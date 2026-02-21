package com.edwinkapkei.formula1.data.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class F1APIService(private val client: HttpClient) {
    suspend fun getCurrentSchedule(year: String): HttpResponse {
        return client.get("f1/$year/races/?format=json")
    }

    suspend fun getCurrentDrivers(year: String): HttpResponse {
        return client.get("f1/$year/driverstandings/?format=json")
    }

    suspend fun getCurrentConstructors(year: String): HttpResponse {
        return client.get("f1/$year/constructorstandings/?format=json")
    }
}
