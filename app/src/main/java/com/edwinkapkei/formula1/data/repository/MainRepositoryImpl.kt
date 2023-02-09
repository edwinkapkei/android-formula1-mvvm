package com.edwinkapkei.formula1.data.repository

import com.edwinkapkei.formula1.data.model.constructor.ConstructorsResponse
import com.edwinkapkei.formula1.data.model.driver.DriversResponse
import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import com.edwinkapkei.formula1.data.repository.dataSource.F1RemoteDataSource
import com.edwinkapkei.formula1.utilities.RequestState
import com.edwinkapkei.formula1.domain.repository.MainRepository

class MainRepositoryImpl(
    private val f1RemoteDataSource: F1RemoteDataSource
) : MainRepository {

    override suspend fun getCurrentSchedule(): RequestState<ScheduleResponse> {
        val response = f1RemoteDataSource.getCurrentSchedule()

        if (response.isSuccessful) {
            response.body()?.let { result ->
                return RequestState.Success(result)
            }
        }

        return RequestState.Error(response.message())
    }

    override suspend fun getCurrentDrivers(): RequestState<DriversResponse> {
        val response = f1RemoteDataSource.getCurrentDrivers()

        if (response.isSuccessful) {
            response.body()?.let { result ->
                return RequestState.Success(result)
            }
        }

        return RequestState.Error(response.message())
    }

    override suspend fun getCurrentConstructors(): RequestState<ConstructorsResponse> {
        val response = f1RemoteDataSource.getCurrentConstructors()

        if (response.isSuccessful) {
            response.body()?.let { result ->
                return RequestState.Success(result)
            }
        }

        return RequestState.Error(response.message())
    }
}