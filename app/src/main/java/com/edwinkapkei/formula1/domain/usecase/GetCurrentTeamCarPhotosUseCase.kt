package com.edwinkapkei.formula1.domain.usecase

import com.edwinkapkei.formula1.domain.repository.MainRepository

class GetCurrentTeamCarPhotosUseCase(private val mainRepository: MainRepository) {
    suspend fun execute(year: String): Map<String, String> {
        return mainRepository.getTeamCarPhotos(year)
    }
}