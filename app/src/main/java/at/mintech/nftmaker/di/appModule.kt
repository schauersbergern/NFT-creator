package at.mintech.nftmaker.di

import android.content.Context
import at.mintech.nftmaker.StartViewModel
import at.mintech.nftmaker.contracts.MyToken
import at.mintech.nftmaker.contracts.NFTContract
import at.mintech.nftmaker.contracts.NFToken
import at.mintech.nftmaker.data.IpfsManager
import at.mintech.nftmaker.domain.*
import at.mintech.nftmaker.domain.GetBalance
import at.mintech.nftmaker.domain.GetTotalSupply
import at.mintech.nftmaker.domain.IpfsUpload
import at.mintech.nftmaker.domain.TransferToken
import at.mintech.nftmaker.domain.TransferNft
import at.mintech.nftmaker.helper.config.*
import at.mintech.nftmaker.helper.navigation.Navigator
import at.mintech.nftmaker.ui.main.MainViewModel
import at.mintech.nftmaker.ui.scan.ScanViewModel
import io.ipfs.multiaddr.MultiAddress
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.web3j.protocol.Web3j
import org.web3j.protocol.infura.InfuraHttpService
import org.web3j.tx.gas.ContractGasProvider
import org.web3j.tx.gas.DefaultGasProvider

const val SHARED_PREFS_NAME = "shared_preferences"

val appModule = module {

    single { IpfsManager(MultiAddress(IPFS_DNS_URL)) }

    single {
        MyToken.load(
            MYTOKEN_CONTRACT_ADDRESS,
            Web3j.build(InfuraHttpService(INFURA_RINKBY_URL)),
            CREDENTIALS,
            DefaultGasProvider()
        )
    }
    single {
        NFTContract.load(
            NFTOKEN_CONTRACT_ADDRESS,
            Web3j.build(InfuraHttpService(INFURA_RINKBY_URL)),
            CREDENTIALS,
            DefaultGasProvider()
        )
    }

    single(named(SHARED_PREFS_NAME)) {
        androidApplication().getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    }

    viewModel { MainViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel { StartViewModel(get()) }
    viewModel { ScanViewModel() }

    factory { GetUserAddress(get(named(SHARED_PREFS_NAME))) }

    factory { IpfsUpload(get()) }
    factory { GetTotalSupply(get()) }
    factory { GetBalance(get()) }
    factory { TransferToken(get()) }

    factory { TransferNft(get()) }
    factory { MintNft(get()) }

    factory { Navigator() }
}