package com.edwinkapkei.formula1.data.repository.dataSourceImpl

import com.edwinkapkei.formula1.data.api.F1APIService
import com.edwinkapkei.formula1.data.model.driverStandings.DriverStandingsResponse
import com.edwinkapkei.formula1.data.model.drivers.DriversResponse
import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import com.edwinkapkei.formula1.data.repository.dataSource.F1RemoteDataSource
import retrofit2.Response

class F1RemoteDataSourceImpl(
    private val f1APIService: F1APIService
) : F1RemoteDataSource {

    override suspend fun getCurrentSchedule(): Response<ScheduleResponse> {
     return f1APIService.getCurrentSchedule()
    }

    override suspend fun getCurrentDrivers(): Response<DriversResponse> {
        return f1APIService.getCurrentDrivers()
    }

    override suspend fun getCurrentDriverStandings(): Response<DriverStandingsResponse> {
       return f1APIService.getCurrentDriverStandings()
    }
}