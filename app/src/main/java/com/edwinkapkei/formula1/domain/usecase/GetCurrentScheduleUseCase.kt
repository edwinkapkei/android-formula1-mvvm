package com.edwinkapkei.formula1.domain.usecase

import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import com.edwinkapkei.formula1.data.util.RequestState
import com.edwinkapkei.formula1.domain.repository.MainRepository

class GetCurrentScheduleUseCase(private val mainRepository: MainRepository) {

    suspend fun execute(): RequestState<ScheduleResponse> {
        return mainRepository.getCurrentSchedule()
    }
}