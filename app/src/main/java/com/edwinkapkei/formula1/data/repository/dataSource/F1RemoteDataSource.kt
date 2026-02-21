package com.edwinkapkei.formula1.data.repository.dataSource

import io.ktor.client.statement.HttpResponse

interface F1RemoteDataSource {
    suspend fun getCurrentSchedule(year: String): HttpResponse

    suspend fun getCurrentDrivers(year: String): HttpResponse

    suspend fun getCurrentConstructors(year: String): HttpResponse
}
