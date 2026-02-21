package com.edwinkapkei.formula1.di

import com.edwinkapkei.formula1.data.api.F1APIService
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import timber.log.Timber

val networkModule = module {
    single {
        HttpClient(Android) {
            install(Logging) {
                logger = object : Logger {
                override fun log(message: String) {
                    Timber.tag("ktor").i(message)
                }
            }
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
            defaultRequest {
                url("https://api.jolpi.ca/ergast/")
            }
        }
    }

    single {
        F1APIService(get())
    }
}
