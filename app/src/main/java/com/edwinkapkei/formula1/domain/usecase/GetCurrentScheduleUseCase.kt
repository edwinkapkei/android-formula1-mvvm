package com.edwinkapkei.formula1.domain.usecase

import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import com.edwinkapkei.formula1.utilities.RequestState
import com.edwinkapkei.formula1.domain.repository.MainRepository

class GetCurrentScheduleUseCase(private val mainRepository: MainRepository) {

    suspend fun execute(year: String): RequestState<ScheduleResponse> {
        return mainRepository.getCurrentSchedule(year)
    }
}