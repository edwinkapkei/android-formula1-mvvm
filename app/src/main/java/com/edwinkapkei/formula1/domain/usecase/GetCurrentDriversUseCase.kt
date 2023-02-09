package com.edwinkapkei.formula1.domain.usecase

import com.edwinkapkei.formula1.data.model.driver.DriversResponse
import com.edwinkapkei.formula1.utilities.RequestState
import com.edwinkapkei.formula1.domain.repository.MainRepository

class GetCurrentDriversUseCase(private val mainRepository: MainRepository) {
    suspend fun execute(): RequestState<DriversResponse> {
        return mainRepository.getCurrentDrivers()
    }
}