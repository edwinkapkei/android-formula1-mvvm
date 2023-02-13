package com.edwinkapkei.formula1.data.api

import com.edwinkapkei.formula1.data.model.constructor.ConstructorsResponse
import com.edwinkapkei.formula1.data.model.driver.DriversResponse
import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface F1APIService {

    @GET("api/f1/{YEAR}.json")
    suspend fun getCurrentSchedule(@Path("YEAR") year: String): Response<ScheduleResponse>

    @GET("api/f1/{YEAR}/driverStandings.json")
    suspend fun getCurrentDrivers(@Path("YEAR") year: String): Response<DriversResponse>

    @GET("api/f1/{YEAR}/constructorStandings.json")
    suspend fun getCurrentConstructors(@Path("YEAR") year: String): Response<ConstructorsResponse>
}