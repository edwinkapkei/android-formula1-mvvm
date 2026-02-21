package com.edwinkapkei.formula1.domain.fakeusecase

import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import com.edwinkapkei.formula1.domain.repository.MainRepository
import com.edwinkapkei.formula1.utilities.RequestState

class FakeGetCurrentScheduleUseCase(private val mainRepository: MainRepository) {
    suspend fun execute(year: String): RequestState<ScheduleResponse> {
        return mainRepository.getCurrentSchedule(year)
    }
}
