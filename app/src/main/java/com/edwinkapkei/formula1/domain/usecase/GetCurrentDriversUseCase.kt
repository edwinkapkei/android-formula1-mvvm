package com.edwinkapkei.formula1.domain.usecase

import com.edwinkapkei.formula1.data.model.driver.DriverAndImage
import com.edwinkapkei.formula1.utilities.RequestState
import com.edwinkapkei.formula1.domain.repository.MainRepository

class GetCurrentDriversUseCase(
    private val mainRepository: MainRepository,
    private val driverPhotosUseCase: GetCurrentDriverPhotosUseCase
) {
    suspend fun execute(): RequestState<List<DriverAndImage>> {
        val driversResponse = mainRepository.getCurrentDrivers()
        val driverList = mutableListOf<DriverAndImage>()
        if (driversResponse is RequestState.Success) {
            val driverPhotos = driverPhotosUseCase.execute()
            for (driver in driversResponse.data.mRData.standingsTable.standingsLists[0].driverStandings) {
                driverList.add(
                    DriverAndImage(
                        driver,
                        driverPhotos[driver.driver.driverId] ?: ""
                    )
                )
            }
        }
        return RequestState.Success(driverList)
    }
}