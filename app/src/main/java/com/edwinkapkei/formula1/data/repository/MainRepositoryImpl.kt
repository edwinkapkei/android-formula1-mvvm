package com.edwinkapkei.formula1.data.repository

import com.edwinkapkei.formula1.data.model.constructor.ConstructorsResponse
import com.edwinkapkei.formula1.data.model.driver.DriversResponse
import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import com.edwinkapkei.formula1.data.repository.dataSource.F1RemoteDataSource
import com.edwinkapkei.formula1.data.repository.dataSource.StaticDriverImages
import com.edwinkapkei.formula1.data.repository.dataSource.StaticTeamCarImages
import com.edwinkapkei.formula1.domain.repository.MainRepository
import com.edwinkapkei.formula1.utilities.RequestState
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException

class MainRepositoryImpl(
    private val f1RemoteDataSource: F1RemoteDataSource
) : MainRepository {
    override suspend fun getCurrentSchedule(year: String): RequestState<ScheduleResponse> {
        return try {
            val response = f1RemoteDataSource.getCurrentSchedule(year)
            RequestState.Success(response)
        } catch (e: RedirectResponseException) {
            RequestState.Error(code = e.response.status.value, message = e.response.status.description)
        } catch (e: ClientRequestException) {
            RequestState.Error(code = e.response.status.value, message = e.response.status.description)
        } catch (e: ServerResponseException) {
            RequestState.Error(code = e.response.status.value, message = e.response.status.description)
        } catch (e: Exception) {
            RequestState.Exception(e)
        }
    }

    override suspend fun getCurrentDrivers(year: String): RequestState<DriversResponse> {
        return try {
            val response = f1RemoteDataSource.getCurrentDrivers(year)
            RequestState.Success(response)
        } catch (e: RedirectResponseException) {
            RequestState.Error(code = e.response.status.value, message = e.response.status.description)
        } catch (e: ClientRequestException) {
            RequestState.Error(code = e.response.status.value, message = e.response.status.description)
        } catch (e: ServerResponseException) {
            RequestState.Error(code = e.response.status.value, message = e.response.status.description)
        } catch (e: Exception) {
            RequestState.Exception(e)
        }
    }

    override suspend fun getCurrentConstructors(year: String): RequestState<ConstructorsResponse> {
        return try {
            val response = f1RemoteDataSource.getCurrentConstructors(year)
            RequestState.Success(response)
        } catch (e: RedirectResponseException) {
            RequestState.Error(code = e.response.status.value, message = e.response.status.description)
        } catch (e: ClientRequestException) {
            RequestState.Error(code = e.response.status.value, message = e.response.status.description)
        } catch (e: ServerResponseException) {
            RequestState.Error(code = e.response.status.value, message = e.response.status.description)
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
