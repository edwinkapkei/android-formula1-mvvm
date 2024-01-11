package com.edwinkapkei.formula1.domain.usecase

import com.edwinkapkei.formula1.data.model.constructor.ConstructorsResponse
import com.edwinkapkei.formula1.domain.repository.MainRepository
import com.edwinkapkei.formula1.utilities.CustomDateFormatter
import com.edwinkapkei.formula1.utilities.RequestState

class GetCurrentConstructorsUseCase(private val mainRepository: MainRepository) {

    suspend fun execute(year: String): RequestState<ConstructorsResponse> {
        val response = mainRepository.getCurrentConstructors(year)
        if (response is RequestState.Success) {
            val standingsList = response.data.mRData.standingsTable.standingsLists
            return if (standingsList.isNotEmpty()) {
                response
            } else {
                getPreviousYear()
            }
        }

        return response
    }

    private suspend fun getPreviousYear(): RequestState<ConstructorsResponse> {
        return mainRepository.getCurrentConstructors(CustomDateFormatter.getPreviousYear())
    }
}