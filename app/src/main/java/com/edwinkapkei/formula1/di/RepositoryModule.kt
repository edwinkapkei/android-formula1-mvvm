package com.edwinkapkei.formula1.di

import com.edwinkapkei.formula1.data.repository.MainRepositoryImpl
import com.edwinkapkei.formula1.domain.repository.MainRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MainRepository> {
        MainRepositoryImpl(get())
    }
}
