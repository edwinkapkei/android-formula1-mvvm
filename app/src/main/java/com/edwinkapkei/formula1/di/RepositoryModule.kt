package com.edwinkapkei.formula1.di

import com.edwinkapkei.formula1.data.repository.MainRepositoryImpl
import com.edwinkapkei.formula1.data.repository.dataSource.F1RemoteDataSource
import com.edwinkapkei.formula1.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(f1RemoteDataSource: F1RemoteDataSource): MainRepository {
        return MainRepositoryImpl(f1RemoteDataSource)
    }
}