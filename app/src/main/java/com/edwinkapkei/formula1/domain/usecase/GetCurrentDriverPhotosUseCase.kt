package com.edwinkapkei.formula1.domain.usecase

import com.edwinkapkei.formula1.domain.repository.MainRepository

class GetCurrentDriverPhotosUseCase(private val mainRepository: MainRepository) {
    suspend fun execute(): Map<String, String> {
        return mainRepository.getDriverPhotos()
    }
}