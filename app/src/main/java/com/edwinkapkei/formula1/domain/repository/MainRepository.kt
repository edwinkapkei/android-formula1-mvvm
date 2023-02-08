package com.edwinkapkei.formula1.domain.repository

import com.edwinkapkei.formula1.data.model.constructor.ConstructorsResponse
import com.edwinkapkei.formula1.data.model.driver.DriversResponse
import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import com.edwinkapkei.formula1.data.util.RequestState

interface MainRepository {

    suspend fun getCurrentSchedule(): RequestState<ScheduleResponse>

    suspend fun getCurrentDrivers(): RequestState<DriversResponse>

    suspend fun getCurrentConstructors(): RequestState<ConstructorsResponse>
}