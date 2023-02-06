package com.edwinkapkei.formula1.di

import com.edwinkapkei.formula1.BuildConfig
import com.edwinkapkei.formula1.data.api.F1APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://ergast.com/")
            .build()
    }

    @Singleton
    @Provides
    fun provideF1ApiService(retrofit: Retrofit): F1APIService {
        return retrofit.create(F1APIService::class.java)
    }
}