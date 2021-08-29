package at.mintech.nftmaker

import android.app.Application
import at.mintech.nftmaker.di.appModule
import org.koin.core.context.startKoin

class NftApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            //androidContext(this@NftApplication)
            modules(appModule)
        }
    }
}