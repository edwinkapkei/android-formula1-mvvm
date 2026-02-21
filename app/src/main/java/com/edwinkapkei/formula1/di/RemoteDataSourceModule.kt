package com.edwinkapkei.formula1.di

import com.edwinkapkei.formula1.data.repository.dataSource.F1RemoteDataSource
import com.edwinkapkei.formula1.data.repository.dataSourceImpl.F1RemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single<F1RemoteDataSource> {
        F1RemoteDataSourceImpl(get())
    }
}
