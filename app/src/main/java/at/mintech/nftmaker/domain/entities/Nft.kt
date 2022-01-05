package at.mintech.nftmaker.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class Nft(
    val nftUrl: String,
    val fileType: String
)