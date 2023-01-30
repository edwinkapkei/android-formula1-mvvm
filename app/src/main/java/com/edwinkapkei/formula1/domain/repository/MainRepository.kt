package com.edwinkapkei.formula1.domain.repository

import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse

interface MainRepository {

    suspend fun getCurrentSchedule(): ScheduleResponse
}