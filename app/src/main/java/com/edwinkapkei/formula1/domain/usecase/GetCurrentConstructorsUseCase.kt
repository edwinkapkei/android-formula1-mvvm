package com.edwinkapkei.formula1.domain.usecase

import com.edwinkapkei.formula1.data.model.constructor.ConstructorAndTeamCarImage
import com.edwinkapkei.formula1.domain.repository.MainRepository
import com.edwinkapkei.formula1.utilities.CustomDateFormatter
import com.edwinkapkei.formula1.utilities.RequestState

class GetCurrentConstructorsUseCase(
    private val mainRepository: MainRepository,
    private val teamCarPhotosUseCase: GetCurrentTeamCarPhotosUseCase
) {
    suspend fun execute(year: String): RequestState<List<ConstructorAndTeamCarImage>> {
        val teamCarPhotos = teamCarPhotosUseCase.execute(year)
        val constructorResponse = mainRepository.getCurrentConstructors(year)
        val constructorList = mutableListOf<ConstructorAndTeamCarImage>()
        if (constructorResponse is RequestState.Success) {
            val standingsList = constructorResponse.data.mRData.standingsTable.standingsLists
            if (standingsList.isNotEmpty()) {
                for (constructor in standingsList[0].constructorStandings) {
                    constructorList.add(
                        ConstructorAndTeamCarImage(
                            constructor,
                            teamCarPhotos[constructor.constructor.constructorId] ?: ""
                        )
                    )
                }
            } else {
                return requestPreviousYear()
            }
        }

        return RequestState.Success(constructorList)
    }

    private suspend fun requestPreviousYear(): RequestState<List<ConstructorAndTeamCarImage>> {
        val teamCarPhotos = teamCarPhotosUseCase.execute(CustomDateFormatter.getPreviousYear())
        val constructorResponse = mainRepository.getCurrentConstructors(CustomDateFormatter.getPreviousYear())
        val constructorList = mutableListOf<ConstructorAndTeamCarImage>()
        if (constructorResponse is RequestState.Success) {
            for (constructor in constructorResponse.data.mRData.standingsTable.standingsLists[0].constructorStandings) {
                constructorList.add(
                    ConstructorAndTeamCarImage(
                        constructor,
                        teamCarPhotos[constructor.constructor.constructorId] ?: ""
                    )
                )
            }
        }

        return RequestState.Success(constructorList)
    }
}
