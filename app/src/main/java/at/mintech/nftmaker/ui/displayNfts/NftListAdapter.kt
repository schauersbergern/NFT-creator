package at.mintech.nftmaker.ui.displayNfts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import at.mintech.nftmaker.R
import at.mintech.nftmaker.domain.entities.DisplayNft

class NftListAdapter(private val nftList: List<DisplayNft>) : RecyclerView.Adapter<NftListAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nftImage: ImageView = view.findViewById(R.id.nft_view) as ImageView
        var nftText: TextView = view.findViewById(R.id.nft_text) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.nft_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var nft = nftList[position]
        var image = holder.nftImage

        when (nft.fileType) {
            "pdf" -> image.setImageDrawable(ContextCompat.getDrawable(image.context, R.drawable.ic_preview_pdf))
            "mp3", "m4a" -> image.setImageDrawable(ContextCompat.getDrawable(image.context, R.drawable.ic_preview_audio))
            "mp4" -> image.setImageDrawable(ContextCompat.getDrawable(image.context,  R.drawable.ic_preview_video))
            "jpeg", "jpg", "png" -> image.setImageBitmap(nft.bitmap)
        }

        holder.nftText.setText("Nft ${position}")
    }

    override fun getItemCount(): Int {
        return nftList.size
    }

}