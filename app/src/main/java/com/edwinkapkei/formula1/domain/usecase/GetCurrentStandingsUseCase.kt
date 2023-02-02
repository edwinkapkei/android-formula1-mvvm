package com.edwinkapkei.formula1.domain.usecase

import com.edwinkapkei.formula1.data.model.standings.StandingsResponse
import com.edwinkapkei.formula1.data.util.RequestState
import com.edwinkapkei.formula1.domain.repository.MainRepository

class GetCurrentStandingsUseCase(private val mainRepository: MainRepository) {

    suspend fun execute(): RequestState<StandingsResponse> {
        return mainRepository.getCurrentStandings()
    }
}