package at.mintech.nftmaker.helper.config

import at.mintech.nftmaker.BuildConfig
import org.web3j.crypto.Credentials
import java.math.BigInteger

const val IPFS_URL = "https://ipfs.io/ipfs/"
const val IPFS_DNS_URL = "/dnsaddr/ipfs.infura.io/tcp/5001/https"

const val INFURA_RINKBY_URL = "https://rinkeby.infura.io/v3/01eb8f7b5e514832af8e827c23784d23"
const val MYTOKEN_CONTRACT_ADDRESS = "0xE2ba7feBCAf4d1B3794f92414f994aaA89f08Dfe"
const val NFTOKEN_CONTRACT_ADDRESS = "0x2BaA098dc911719F93a286dc5f73b879D34AdDb6"

const val ACCOUNT_SENDER = "0x13b365B4349E108a35A0FAD0a9564D7af837c055"
const val ACCOUNT_RECEIVER = "0x468Dd3b7e12dc827fb4Ba40cE50bf9605e112Cf8"

const val SUPPORTED_FILE_TYPES = "jpg|png|jpeg|pdf|mp4|mp3|m4a"
const val ADDRESS_REGEX = "^0x[a-fA-F0-9]{40}"
val ADDRESS_PREFIXES = listOf("ethereum:")

val CREDENTIALS: Credentials = Credentials.create(BuildConfig.PRIVATE_KEY)

const val INVALID_ADDRESS = "Invalid Ethereum Address"
const val USER_ADDRESS_KEY = "userAddress"