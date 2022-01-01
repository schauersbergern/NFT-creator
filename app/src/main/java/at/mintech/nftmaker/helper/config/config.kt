package at.mintech.nftmaker.helper.config

import org.web3j.crypto.Credentials
import java.math.BigInteger

const val IPFS_URL = "https://ipfs.io/ipfs/"
const val IPFS_DNS_URL = "/dnsaddr/ipfs.infura.io/tcp/5001/https"

const val INFURA_RINKBY_URL = "https://rinkeby.infura.io/v3/01eb8f7b5e514832af8e827c23784d23"
const val MYTOKEN_CONTRACT_ADDRESS = "0xE2ba7feBCAf4d1B3794f92414f994aaA89f08Dfe"
const val NFTOKEN_CONTRACT_ADDRESS = "0x2BaA098dc911719F93a286dc5f73b879D34AdDb6"
const val PRIVATE_KEY = "b3ee087557782766d298252c90248d0395a50349c7c3aea2643884ffbaa4070c"

const val ACCOUNT_SENDER = "0x13b365B4349E108a35A0FAD0a9564D7af837c055"
const val ACCOUNT_RECEIVER = "0x468Dd3b7e12dc827fb4Ba40cE50bf9605e112Cf8"

const val SUPPORTED_FILE_TYPES = "jpg|png|jpeg|pdf|mp4|mp3|m4a"

val GAS_LIMIT: BigInteger = BigInteger.valueOf(20_000_000_000L)
val GAS_PRIZE: BigInteger = BigInteger.valueOf(4300000)

val CREDENTIALS = Credentials.create(PRIVATE_KEY)