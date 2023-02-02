package com.edwinkapkei.formula1.domain.repository

import com.edwinkapkei.formula1.data.model.drivers.DriversResponse
import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import com.edwinkapkei.formula1.data.model.driverStandings.DriverStandingsResponse
import com.edwinkapkei.formula1.data.util.RequestState

interface MainRepository {

    suspend fun getCurrentSchedule(): RequestState<ScheduleResponse>

    suspend fun getCurrentDrivers(): RequestState<DriversResponse>

    suspend fun getCurrentDriverStandings(): RequestState<DriverStandingsResponse>
}