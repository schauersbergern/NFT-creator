package at.mintech.nftmaker.ui.createNft

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import at.mintech.nftmaker.data.IpfsManager
import at.mintech.nftmaker.domain.GetImageBmp
import at.mintech.nftmaker.domain.GetUserAddress
import at.mintech.nftmaker.domain.MintNft
import at.mintech.nftmaker.domain.entities.MintParams
import at.mintech.nftmaker.helper.config.IPFS_URL
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import java.math.BigInteger

data class CreateNftViewModelState(
    val nftUrl: String = "",
    val fileType: String = "",
    val previewBmp: Bitmap? = null
)

sealed class CreateNftViewModelSideEffects {
    object Loading : CreateNftViewModelSideEffects()
    object ContentLoaded : CreateNftViewModelSideEffects()
    object NftMinted : CreateNftViewModelSideEffects()
    data class ShowError(val error: String) : CreateNftViewModelSideEffects()
}

internal class CreateNftViewModel(
    private val ipfsManager: IpfsManager,
    private val getUserAddress: GetUserAddress,
    private val mintNft: MintNft,
    private val getImageBmp: GetImageBmp
) : ViewModel(), ContainerHost<CreateNftViewModelState, CreateNftViewModelSideEffects> {

    override val container =
        container<CreateNftViewModelState, CreateNftViewModelSideEffects>(CreateNftViewModelState())

    fun uploadData(data: ByteArray, fileType: String) = intent {
        postSideEffect(CreateNftViewModelSideEffects.Loading)
        //val ipfsUrl = IPFS_URL + ipfsManager.addFile(FILENAME, data)
        val ipfsUrl = "https://ipfs.io/ipfs/QmUzR3Riu88brJBEx3Rr4J8EHDi3SSSGH2z4ZyiYVRb6Xp"
        reduce {
            state.copy(nftUrl = ipfsUrl, fileType = fileType)
        }
        postSideEffect(CreateNftViewModelSideEffects.ContentLoaded)
    }

    fun mintNft() = intent {
        postSideEffect(CreateNftViewModelSideEffects.Loading)

        mintNft(
            MintParams(
                getUserAddress(Unit).getOrDefault(DEFAULT_ADDRESS),
                BigInteger("1"),
                state.nftUrl
            )
        ).fold(
            { postSideEffect(CreateNftViewModelSideEffects.NftMinted) },
            { postSideEffect(CreateNftViewModelSideEffects.ShowError(it.message ?: DEFAULT_ERROR_MSG)) }
        )

        postSideEffect(CreateNftViewModelSideEffects.ContentLoaded)
    }

    companion object {
        const val FILENAME = "Testfile"
        const val DEFAULT_ADDRESS = ""
        const val DEFAULT_ERROR_MSG = "An unknown error happened"
    }

}