package com.edwinkapkei.formula1.domain.repository

import com.edwinkapkei.formula1.data.model.constructor.ConstructorsResponse
import com.edwinkapkei.formula1.data.model.driver.DriversResponse
import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import com.edwinkapkei.formula1.utilities.RequestState

interface MainRepository {

    suspend fun getCurrentSchedule(year: String): RequestState<ScheduleResponse>

    suspend fun getCurrentDrivers(year: String): RequestState<DriversResponse>

    suspend fun getCurrentConstructors(year: String): RequestState<ConstructorsResponse>

    suspend fun getDriverPhotos(): Map<String, String>

    suspend fun getTeamCarPhotos(year: String): Map<String, String>
}