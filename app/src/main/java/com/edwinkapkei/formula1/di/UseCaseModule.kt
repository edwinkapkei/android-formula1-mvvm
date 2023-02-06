package com.edwinkapkei.formula1.di

import com.edwinkapkei.formula1.domain.repository.MainRepository
import com.edwinkapkei.formula1.domain.usecase.GetCurrentDriverStandingsUseCase
import com.edwinkapkei.formula1.domain.usecase.GetCurrentDriversUseCase
import com.edwinkapkei.formula1.domain.usecase.GetCurrentScheduleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideCurrentScheduleUseCase(mainRepository: MainRepository): GetCurrentScheduleUseCase {
        return GetCurrentScheduleUseCase(mainRepository)
    }

    @Singleton
    @Provides
    fun provideCurrentDriversUseCase(mainRepository: MainRepository): GetCurrentDriversUseCase {
        return GetCurrentDriversUseCase(mainRepository)
    }

    @Singleton
    @Provides
    fun provideCurrentDriverStandingsUseCase(mainRepository: MainRepository): GetCurrentDriverStandingsUseCase {
        return GetCurrentDriverStandingsUseCase(mainRepository)
    }
}