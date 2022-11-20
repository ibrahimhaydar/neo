package com.projects.neo.domain.repository

import com.projects.neo.data.model.ResponseHistorical
import com.projects.neo.data.model.ResponsePortfolios
import com.projects.neo.data.util.Resource

interface HistoricalRepository {
    suspend fun getHistorical(): Resource<ResponseHistorical>?
}