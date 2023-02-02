package com.edwinkapkei.formula1.data.api

import com.edwinkapkei.formula1.data.model.drivers.DriversResponse
import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import com.edwinkapkei.formula1.data.model.driverStandings.DriverStandingsResponse
import retrofit2.Response
import retrofit2.http.GET

interface F1APIService {

    @GET("api/f1/2023.json")
    suspend fun getCurrentSchedule(): Response<ScheduleResponse>

    @GET("api/f1/current/drivers.json")
    suspend fun getCurrentDrivers(): Response<DriversResponse>

    @GET("api/f1/current/driverStandings.json")
    suspend fun getCurrentDriverStandings(): Response<DriverStandingsResponse>
}