package com.edwinkapkei.formula1.data.repository.dataSource

import com.edwinkapkei.formula1.data.model.driverStandings.DriverStandingsResponse
import com.edwinkapkei.formula1.data.model.drivers.Driver
import com.edwinkapkei.formula1.data.model.drivers.DriversResponse
import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import retrofit2.Response

interface F1RemoteDataSource {
    suspend fun getCurrentSchedule(): Response<ScheduleResponse>

    suspend fun getCurrentDrivers(): Response<DriversResponse>

    suspend fun getCurrentDriverStandings(): Response<DriverStandingsResponse>
}