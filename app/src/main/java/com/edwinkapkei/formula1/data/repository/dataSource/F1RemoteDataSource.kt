package com.edwinkapkei.formula1.data.repository.dataSource

import com.edwinkapkei.formula1.data.model.constructor.ConstructorsResponse
import com.edwinkapkei.formula1.data.model.driver.DriversResponse
import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import retrofit2.Response

interface F1RemoteDataSource {
    suspend fun getCurrentSchedule(year: String): Response<ScheduleResponse>

    suspend fun getCurrentDrivers(year: String): Response<DriversResponse>

    suspend fun getCurrentConstructors(year: String): Response<ConstructorsResponse>
}
