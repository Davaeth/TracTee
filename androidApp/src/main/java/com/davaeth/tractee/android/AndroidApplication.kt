package com.davaeth.tractee.android

import android.app.Application
import com.davaeth.tractee.android.ui.di.domainMappersModule
import com.davaeth.tractee.android.ui.di.domainUseCasesModule
import com.davaeth.tractee.android.ui.di.repositoryModule
import com.davaeth.tractee.android.ui.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AndroidApplication)
            androidLogger(Level.DEBUG)
            modules(uiModule, repositoryModule, domainMappersModule, domainUseCasesModule)
        }
    }
}