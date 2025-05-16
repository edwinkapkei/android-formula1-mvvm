package com.edwinkapkei.formula1.data.api

import com.edwinkapkei.formula1.data.model.constructor.ConstructorsResponse
import com.edwinkapkei.formula1.data.model.driver.DriversResponse
import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface F1APIService {

    @GET("f1/{YEAR}/races/?format=json")
    suspend fun getCurrentSchedule(@Path("YEAR") year: String): Response<ScheduleResponse>

    @GET("f1/{YEAR}/driverstandings/?format=json")
    suspend fun getCurrentDrivers(@Path("YEAR") year: String): Response<DriversResponse>

    @GET("f1/{YEAR}/constructorstandings/?format=json")
    suspend fun getCurrentConstructors(@Path("YEAR") year: String): Response<ConstructorsResponse>
}