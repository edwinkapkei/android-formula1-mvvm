package com.edwinkapkei.formula1.di

import com.edwinkapkei.formula1.BuildConfig
import com.edwinkapkei.formula1.data.api.F1APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(getOkhttpClientBuilder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.jolpi.ca/ergast/")
            .build()
    }

    @Singleton
    @Provides
    fun provideF1ApiService(retrofit: Retrofit): F1APIService {
        return retrofit.create(F1APIService::class.java)
    }

    private fun getOkhttpClientBuilder(): OkHttpClient.Builder {
        val timeout = 60L
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().apply {
            connectTimeout(timeout, TimeUnit.SECONDS)
            readTimeout(timeout, TimeUnit.SECONDS)
            writeTimeout(timeout, TimeUnit.SECONDS)
            callTimeout(timeout, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                addInterceptor(interceptor)
            }
        }
    }
}
