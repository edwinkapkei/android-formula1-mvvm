package com.edwinkapkei.formula1.views.di

import android.app.Application
import com.edwinkapkei.formula1.domain.usecase.GetCurrentScheduleUseCase
import com.edwinkapkei.formula1.views.viewmodel.CurrentScheduleViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ViewModelFactoryModule {

    @Singleton
    @Provides
    fun provideCurrentScheduleViewModelFactory(
        application: Application,
        getCurrentScheduleUseCase: GetCurrentScheduleUseCase
    ): CurrentScheduleViewModelFactory {
        return CurrentScheduleViewModelFactory(application, getCurrentScheduleUseCase)
    }
}