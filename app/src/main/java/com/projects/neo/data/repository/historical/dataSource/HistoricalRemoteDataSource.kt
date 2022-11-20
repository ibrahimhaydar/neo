package com.projects.neo.data.repository.historical.dataSource

import com.projects.neo.data.model.ResponseHistorical
import com.projects.neo.data.model.ResponsePortfolios
import retrofit2.Response

interface HistoricalRemoteDataSource {
    suspend fun getHistorical(): Response<ResponseHistorical>

}