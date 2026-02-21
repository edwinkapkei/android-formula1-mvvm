package com.edwinkapkei.formula1.data.repository

import com.edwinkapkei.formula1.data.model.constructor.ConstructorsResponse
import com.edwinkapkei.formula1.data.model.driver.DriversResponse
import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import com.edwinkapkei.formula1.data.repository.dataSource.F1RemoteDataSource
import com.edwinkapkei.formula1.data.repository.dataSource.StaticDriverImages
import com.edwinkapkei.formula1.data.repository.dataSource.StaticTeamCarImages
import com.edwinkapkei.formula1.domain.repository.MainRepository
import com.edwinkapkei.formula1.utilities.RequestState

class MainRepositoryImpl(
    private val f1RemoteDataSource: F1RemoteDataSource
) : MainRepository {
    override suspend fun getCurrentSchedule(year: String): RequestState<ScheduleResponse> {
        return RequestState.safeApiCall {
            f1RemoteDataSource.getCurrentSchedule(year)
        }
    }

    override suspend fun getCurrentDrivers(year: String): RequestState<DriversResponse> {
        return RequestState.safeApiCall {
            f1RemoteDataSource.getCurrentDrivers(year)
        }
    }

    override suspend fun getCurrentConstructors(year: String): RequestState<ConstructorsResponse> {
        return RequestState.safeApiCall {
            f1RemoteDataSource.getCurrentConstructors(year)
        }
    }

    override suspend fun getDriverPhotos(): Map<String, String> {
        return StaticDriverImages.getDriverImages()
    }

    override suspend fun getTeamCarPhotos(year: String): Map<String, String> {
        return StaticTeamCarImages.getTeamCarImages(year)
    }
}
