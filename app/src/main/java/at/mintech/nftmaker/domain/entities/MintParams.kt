package at.mintech.nftmaker.domain.entities

import java.math.BigInteger

data class MintParams(
    val address: String,
    val tokenId: BigInteger,
    val nftUrl: String
)
