package com.edwinkapkei.formula1.data.repository.dataSource

import com.edwinkapkei.formula1.data.model.constructor.ConstructorsResponse
import com.edwinkapkei.formula1.data.model.driver.DriversResponse
import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse

interface F1RemoteDataSource {
    suspend fun getCurrentSchedule(year: String): ScheduleResponse

    suspend fun getCurrentDrivers(year: String): DriversResponse

    suspend fun getCurrentConstructors(year: String): ConstructorsResponse
}
