package com.projects.neo.data.repository.historical.dataSourceImpl

import com.projects.neo.data.api.ApiService
import com.projects.neo.data.model.*
import com.projects.neo.data.repository.historical.dataSource.HistoricalRemoteDataSource
import retrofit2.Response

class HistoricalRemoteDataSourceImpl(private val apiService: ApiService):HistoricalRemoteDataSource{
    override suspend fun getHistorical(): Response<ResponseHistorical> {
        return apiService.getHistorical()
    }
}