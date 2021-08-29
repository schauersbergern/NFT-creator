package at.mintech.nftmaker.domain.entities

import java.math.BigInteger

data class TransferParams(
    val address: String,
    val tokenId: BigInteger
)
