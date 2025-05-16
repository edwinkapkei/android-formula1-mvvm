package com.edwinkapkei.formula1.domain.usecase

import com.edwinkapkei.formula1.data.model.driver.DriverAndImage
import com.edwinkapkei.formula1.domain.repository.MainRepository
import com.edwinkapkei.formula1.utilities.CustomDateFormatter
import com.edwinkapkei.formula1.utilities.RequestState

class GetCurrentDriversUseCase(
    private val mainRepository: MainRepository,
    private val driverPhotosUseCase: GetCurrentDriverPhotosUseCase,
) {
    suspend fun execute(year: String): RequestState<List<DriverAndImage>> {
        val driversResponse = mainRepository.getCurrentDrivers(year)
        val driverList = mutableListOf<DriverAndImage>()
        val driverPhotos = driverPhotosUseCase.execute()
        if (driversResponse is RequestState.Success) {
            val standingsList = driversResponse.data.mRData.standingsTable.standingsLists
            if (standingsList.isNotEmpty()) {
                for (driver in driversResponse.data.mRData.standingsTable.standingsLists[0].driverStandings) {
                    driverList.add(DriverAndImage(driver, driverPhotos[driver.driver.driverId] ?: ""))
                }
            } else {
                return requestPreviousYear(driverPhotos)
            }
        }

        return RequestState.Success(driverList)
    }

    private suspend fun requestPreviousYear(driverPhotos: Map<String, String>): RequestState<List<DriverAndImage>> {
        val driversResponse = mainRepository.getCurrentDrivers(CustomDateFormatter.getPreviousYear())
        val driverList = mutableListOf<DriverAndImage>()
        if (driversResponse is RequestState.Success) {
            for (driver in driversResponse.data.mRData.standingsTable.standingsLists[0].driverStandings) {
                driverList.add(DriverAndImage(driver, driverPhotos[driver.driver.driverId] ?: ""))
            }
        }

        return RequestState.Success(driverList)
    }
}
