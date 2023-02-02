package com.edwinkapkei.formula1.domain.usecase

import com.edwinkapkei.formula1.data.model.driverStandings.DriverStandingsResponse
import com.edwinkapkei.formula1.data.util.RequestState
import com.edwinkapkei.formula1.domain.repository.MainRepository

class GetCurrentDriverStandingsUseCase(private val mainRepository: MainRepository) {

    suspend fun execute(): RequestState<DriverStandingsResponse> {
        return mainRepository.getCurrentDriverStandings()
    }
}