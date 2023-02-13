package com.edwinkapkei.formula1.domain.usecase

import com.edwinkapkei.formula1.data.model.driver.DriverAndImage
import com.edwinkapkei.formula1.utilities.RequestState
import com.edwinkapkei.formula1.domain.repository.MainRepository
import java.time.Year

class GetCurrentDriversUseCase(
    private val mainRepository: MainRepository,
    private val driverPhotosUseCase: GetCurrentDriverPhotosUseCase
) {
    suspend fun execute(year: String): RequestState<List<DriverAndImage>> {
        val driversResponse = mainRepository.getCurrentDrivers(year)
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