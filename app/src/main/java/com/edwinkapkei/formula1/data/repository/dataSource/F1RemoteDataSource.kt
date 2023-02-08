package com.edwinkapkei.formula1.data.repository.dataSource

import com.edwinkapkei.formula1.data.model.constructor.ConstructorsResponse
import com.edwinkapkei.formula1.data.model.driver.DriversResponse
import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import retrofit2.Response

interface F1RemoteDataSource {
    suspend fun getCurrentSchedule(): Response<ScheduleResponse>

    suspend fun getCurrentDrivers(): Response<DriversResponse>

    suspend fun getCurrentConstructors(): Response<ConstructorsResponse>
}