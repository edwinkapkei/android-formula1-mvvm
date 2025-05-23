package com.edwinkapkei.formula1.data.repository.dataSourceImpl

import com.edwinkapkei.formula1.data.api.F1APIService
import com.edwinkapkei.formula1.data.model.constructor.ConstructorsResponse
import com.edwinkapkei.formula1.data.model.driver.DriversResponse
import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import com.edwinkapkei.formula1.data.repository.dataSource.F1RemoteDataSource
import retrofit2.Response

class F1RemoteDataSourceImpl(
    private val f1APIService: F1APIService,
) : F1RemoteDataSource {
    override suspend fun getCurrentSchedule(year: String): Response<ScheduleResponse> {
        return f1APIService.getCurrentSchedule(year)
    }

    override suspend fun getCurrentDrivers(year: String): Response<DriversResponse> {
        return f1APIService.getCurrentDrivers(year)
    }

    override suspend fun getCurrentConstructors(year: String): Response<ConstructorsResponse> {
        return f1APIService.getCurrentConstructors(year)
    }
}
