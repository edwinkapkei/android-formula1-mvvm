package com.edwinkapkei.formula1.data.repository.dataSourceImpl

import com.edwinkapkei.formula1.data.api.F1APIService
import com.edwinkapkei.formula1.data.repository.dataSource.F1RemoteDataSource
import io.ktor.client.statement.HttpResponse

class F1RemoteDataSourceImpl(
    private val f1APIService: F1APIService
) : F1RemoteDataSource {
    override suspend fun getCurrentSchedule(year: String): HttpResponse {
        return f1APIService.getCurrentSchedule(year)
    }

    override suspend fun getCurrentDrivers(year: String): HttpResponse {
        return f1APIService.getCurrentDrivers(year)
    }

    override suspend fun getCurrentConstructors(year: String): HttpResponse {
        return f1APIService.getCurrentConstructors(year)
    }
}
