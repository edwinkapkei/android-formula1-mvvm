package com.edwinkapkei.formula1.data.repository

import com.edwinkapkei.formula1.data.model.constructor.ConstructorsResponse
import com.edwinkapkei.formula1.data.model.driver.DriversResponse
import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import com.edwinkapkei.formula1.data.repository.dataSource.F1RemoteDataSource
import com.edwinkapkei.formula1.data.repository.dataSource.StaticDriverImages
import com.edwinkapkei.formula1.data.repository.dataSource.StaticTeamCarImages
import com.edwinkapkei.formula1.domain.repository.MainRepository
import com.edwinkapkei.formula1.utilities.RequestState
import retrofit2.HttpException

class MainRepositoryImpl(
    private val f1RemoteDataSource: F1RemoteDataSource
) : MainRepository {

    override suspend fun getCurrentSchedule(year: String): RequestState<ScheduleResponse> {
        val response = f1RemoteDataSource.getCurrentSchedule(year)

        return try {
            val body = response.body()
            if (response.isSuccessful && body != null) {
                RequestState.Success(body)
            } else {
                RequestState.Error(code = response.code(), message = response.message())
            }
        } catch (e: HttpException) {
            RequestState.Error(code = e.code(), message = e.message())
        } catch (e: Exception) {
            RequestState.Exception(e)
        }
    }

    override suspend fun getCurrentDrivers(year: String): RequestState<DriversResponse> {
        val response = f1RemoteDataSource.getCurrentDrivers(year)

        return try {
            val body = response.body()
            if (response.isSuccessful && body != null) {
                RequestState.Success(body)
            } else {
                RequestState.Error(code = response.code(), message = response.message())
            }
        } catch (e: HttpException) {
            RequestState.Error(code = e.code(), message = e.message())
        } catch (e: Exception) {
            RequestState.Exception(e)
        }
    }

    override suspend fun getCurrentConstructors(year: String): RequestState<ConstructorsResponse> {
        val response = f1RemoteDataSource.getCurrentConstructors(year)

        return try {
            val body = response.body()
            if (response.isSuccessful && body != null) {
                RequestState.Success(body)
            } else {
                RequestState.Error(code = response.code(), message = response.message())
            }
        } catch (e: HttpException) {
            RequestState.Error(code = e.code(), message = e.message())
        } catch (e: Exception) {
            RequestState.Exception(e)
        }
    }

    override suspend fun getDriverPhotos(): Map<String, String> {
        return StaticDriverImages.getDriverImages()
    }

    override suspend fun getTeamCarPhotos(year: String): Map<String, String> {
        return StaticTeamCarImages.getTeamCarImages(year)
    }
}