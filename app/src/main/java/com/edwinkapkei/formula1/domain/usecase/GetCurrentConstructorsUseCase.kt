package com.edwinkapkei.formula1.domain.usecase

import com.edwinkapkei.formula1.data.model.constructor.ConstructorsResponse
import com.edwinkapkei.formula1.utilities.RequestState
import com.edwinkapkei.formula1.domain.repository.MainRepository

class GetCurrentConstructorsUseCase(private val mainRepository: MainRepository) {

    suspend fun execute(): RequestState<ConstructorsResponse> {
        return mainRepository.getCurrentConstructors()
    }
}