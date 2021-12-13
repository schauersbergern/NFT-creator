package at.mintech.nftmaker.ui.scan

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import at.mintech.nftmaker.R
import at.mintech.nftmaker.databinding.ScanFragmentBinding
import at.mintech.nftmaker.helper.delegates.viewBinding
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScanFragment : Fragment(R.layout.scan_fragment) {

    private var codeScanner: CodeScanner? = null
    private val binding by viewBinding(ScanFragmentBinding::bind)
    private val viewModel by viewModel<ScanViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        observeState()
    }

    private fun initUI() {
        initScanner()
        binding.button.setOnClickListener {
            showScanner()
            requestPermissions()
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.container.stateFlow.collect { state ->
                binding.ethereumAddress.setText(state.ethereumAddress)
            }
        }
    }

    private fun showScanner() {
        binding.addressInput.visibility = View.GONE
        binding.scanCamera.visibility = View.VISIBLE
        codeScanner?.startPreview()
    }

    private fun showAddressInput() {
        binding.addressInput.visibility = View.VISIBLE
        binding.scanCamera.visibility = View.GONE
        codeScanner?.releaseResources()
    }

    private fun initScanner() {
        codeScanner = CodeScanner(requireContext(), binding.scannerView)
        codeScanner?.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS
            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                requireActivity().runOnUiThread {
                    onScanFinished(it.text)
                }
            }

            errorCallback = ErrorCallback {
                requireActivity().runOnUiThread {
                    //Todo: Timber
                    Log.e("ScanFragment", "Camera initialization error: ${it.message}")
                }
            }
        }
    }

    private fun onScanFinished(text : String) {
        showAddressInput()
        viewModel.setEthereumAddress(text)
    }

    override fun onResume() {
        super.onResume()
        codeScanner?.startPreview()
    }

    override fun onPause() {
        super.onPause()
        codeScanner?.releaseResources()
    }

    private fun requestPermissions() {
        val permission : Int = ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            permissionRequest()
        }
    }

    private fun permissionRequest() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(requireContext(), "You need Camera Permission", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        fun newInstance() = ScanFragment()
        private const val CAMERA_REQUEST_CODE = 200
    }

}