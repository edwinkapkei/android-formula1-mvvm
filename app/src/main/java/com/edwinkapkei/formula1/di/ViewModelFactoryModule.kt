package com.edwinkapkei.formula1.di

import android.app.Application
import com.edwinkapkei.formula1.domain.usecase.GetCurrentDriversUseCase
import com.edwinkapkei.formula1.domain.usecase.GetCurrentScheduleUseCase
import com.edwinkapkei.formula1.views.viewmodel.CurrentDriversViewModelFactory
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

    @Singleton
    @Provides
    fun provideCurrentDriversViewModelFactory(
        application: Application,
        getCurrentDriversUseCase: GetCurrentDriversUseCase
    ): CurrentDriversViewModelFactory {
        return CurrentDriversViewModelFactory(application, getCurrentDriversUseCase)
    }
}