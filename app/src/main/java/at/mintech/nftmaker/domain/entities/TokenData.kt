package at.mintech.nftmaker.domain.entities

import java.math.BigInteger

data class TokenData(
    val totalSupply: BigInteger,
    val creatorAccountBalance: BigInteger,
    val receiverAccountBalance: BigInteger
)
