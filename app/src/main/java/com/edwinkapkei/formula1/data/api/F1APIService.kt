package com.edwinkapkei.formula1.data.api

import com.edwinkapkei.formula1.data.model.constructor.ConstructorsResponse
import com.edwinkapkei.formula1.data.model.driver.DriversResponse
import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
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
