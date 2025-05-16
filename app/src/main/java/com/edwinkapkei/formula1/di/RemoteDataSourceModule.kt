package com.edwinkapkei.formula1.di

import com.edwinkapkei.formula1.data.api.F1APIService
import com.edwinkapkei.formula1.data.repository.dataSource.F1RemoteDataSource
import com.edwinkapkei.formula1.data.repository.dataSourceImpl.F1RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataSourceModule {
    @Singleton
    @Provides
    fun provideF1RemoteDataSource(f1APIService: F1APIService): F1RemoteDataSource {
        return F1RemoteDataSourceImpl(f1APIService)
    }
}
