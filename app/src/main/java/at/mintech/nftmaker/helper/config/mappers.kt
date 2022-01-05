package at.mintech.nftmaker.helper.config

import at.mintech.nftmaker.domain.entities.Nft
import at.mintech.nftmaker.ui.createNft.CreateNftViewModelState

fun CreateNftViewModelState.toNft() : Nft {
    return Nft(this.nftUrl, this.fileType)
}