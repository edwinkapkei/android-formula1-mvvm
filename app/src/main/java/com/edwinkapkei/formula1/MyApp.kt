package com.edwinkapkei.formula1

import android.app.Application
import com.edwinkapkei.formula1.di.networkModule
import com.edwinkapkei.formula1.di.remoteDataSourceModule
import com.edwinkapkei.formula1.di.repositoryModule
import com.edwinkapkei.formula1.di.useCaseModule
import com.edwinkapkei.formula1.views.viewmodel.driversViewModelModule
import com.edwinkapkei.formula1.views.viewmodel.scheduleViewModelModule
import com.edwinkapkei.formula1.views.viewmodel.teamsViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.Forest.plant

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(
                networkModule,
                remoteDataSourceModule,
                repositoryModule,
                useCaseModule,
                scheduleViewModelModule,
                driversViewModelModule,
                teamsViewModelModule
            )
        }
    }
}
