package at.mintech.nftmaker.di

import at.mintech.nftmaker.contracts.MyToken
import at.mintech.nftmaker.contracts.NFToken
import at.mintech.nftmaker.data.IpfsManager
import at.mintech.nftmaker.domain.*
import at.mintech.nftmaker.domain.GetBalance
import at.mintech.nftmaker.domain.GetTotalSupply
import at.mintech.nftmaker.domain.IpfsUpload
import at.mintech.nftmaker.domain.TransferToken
import at.mintech.nftmaker.domain.TransferNft
import at.mintech.nftmaker.helper.config.*
import at.mintech.nftmaker.ui.main.MainViewModel
import io.ipfs.api.IPFS
import io.ipfs.multiaddr.MultiAddress
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.web3j.protocol.Web3j
import org.web3j.protocol.infura.InfuraHttpService

val appModule = module {

    single { IpfsManager(MultiAddress(IPFS_DNS_URL)) }

    single {
        MyToken.load(
            MYTOKEN_CONTRACT_ADDRESS,
            Web3j.build(InfuraHttpService(INFURA_RINKBY_URL)),
            CREDENTIALS,
            GAS_LIMIT,
            GAS_PRIZE
        )
    }
    single {
        NFToken.load(
            NFTOKEN_CONTRACT_ADDRESS,
            Web3j.build(InfuraHttpService(INFURA_RINKBY_URL)),
            CREDENTIALS,
            GAS_LIMIT,
            GAS_PRIZE
        )
    }
    viewModel { MainViewModel(get(), get(), get(), get()) }

    factory { IpfsUpload(get()) }
    factory { GetTotalSupply(get()) }
    factory { GetBalance(get()) }
    factory { TransferToken(get()) }

    factory { TransferNft(get()) }
    factory { MintNft(get()) }
}