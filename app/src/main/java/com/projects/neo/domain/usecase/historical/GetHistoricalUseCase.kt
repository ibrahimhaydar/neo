package com.projects.neo.domain.usecase.historical

import com.projects.neo.data.model.ResponseHistorical
import com.projects.neo.data.model.ResponsePortfolios
import com.projects.neo.data.model.ResponseStrings
import com.projects.neo.data.util.Resource
import com.projects.neo.domain.repository.HistoricalRepository
import com.projects.neo.domain.repository.HomeRepository
import com.projects.neo.domain.repository.SplashRepository

class GetHistoricalUseCase(private val historicalRepository: HistoricalRepository) {
    suspend fun execute(): Resource<ResponseHistorical>? = historicalRepository.getHistorical()

}