package com.edwinkapkei.formula1.di

import com.edwinkapkei.formula1.domain.repository.MainRepository
import com.edwinkapkei.formula1.domain.usecase.GetCurrentConstructorsUseCase
import com.edwinkapkei.formula1.domain.usecase.GetCurrentDriverPhotosUseCase
import com.edwinkapkei.formula1.domain.usecase.GetCurrentDriversUseCase
import com.edwinkapkei.formula1.domain.usecase.GetCurrentScheduleUseCase
import com.edwinkapkei.formula1.domain.usecase.GetCurrentTeamCarPhotosUseCase
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
    fun provideCurrentDriversUseCase(
        mainRepository: MainRepository,
        driverPhotosUseCase: GetCurrentDriverPhotosUseCase,
    ): GetCurrentDriversUseCase {
        return GetCurrentDriversUseCase(mainRepository, driverPhotosUseCase)
    }

    @Singleton
    @Provides
    fun provideCurrentConstructorsUseCase(
        mainRepository: MainRepository,
        teamCarPhotosUseCase: GetCurrentTeamCarPhotosUseCase,
    ): GetCurrentConstructorsUseCase {
        return GetCurrentConstructorsUseCase(mainRepository, teamCarPhotosUseCase)
    }

    @Singleton
    @Provides
    fun provideCurrentDriversPhotosUseCase(mainRepository: MainRepository): GetCurrentDriverPhotosUseCase {
        return GetCurrentDriverPhotosUseCase(mainRepository)
    }

    @Singleton
    @Provides
    fun provideTeamCarPhotosUseCase(mainRepository: MainRepository): GetCurrentTeamCarPhotosUseCase {
        return GetCurrentTeamCarPhotosUseCase(mainRepository)
    }
}
