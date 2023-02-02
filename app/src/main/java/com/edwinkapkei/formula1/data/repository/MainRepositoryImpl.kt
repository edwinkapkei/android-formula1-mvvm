package com.edwinkapkei.formula1.data.repository

import com.edwinkapkei.formula1.data.model.driverStandings.DriverStandingsResponse
import com.edwinkapkei.formula1.data.model.drivers.DriversResponse
import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import com.edwinkapkei.formula1.data.repository.dataSource.F1RemoteDataSource
import com.edwinkapkei.formula1.data.util.RequestState
import com.edwinkapkei.formula1.domain.repository.MainRepository
import retrofit2.Response

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

    override suspend fun getCurrentDriverStandings(): RequestState<DriverStandingsResponse> {
        val response = f1RemoteDataSource.getCurrentDriverStandings()

        if (response.isSuccessful) {
            response.body()?.let { result ->
                return RequestState.Success(result)
            }
        }

        return RequestState.Error(response.message())
    }
}