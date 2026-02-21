package com.edwinkapkei.formula1.di

import com.edwinkapkei.formula1.BuildConfig
import com.edwinkapkei.formula1.data.api.F1APIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val timeout = 60L
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder().apply {
            connectTimeout(timeout, TimeUnit.SECONDS)
            readTimeout(timeout, TimeUnit.SECONDS)
            writeTimeout(timeout, TimeUnit.SECONDS)
            callTimeout(timeout, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                addInterceptor(interceptor)
            }
        }.build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.jolpi.ca/ergast/")
            .build()
    }

    single {
        get<Retrofit>().create(F1APIService::class.java)
    }
}
