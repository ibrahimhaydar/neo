package com.projects.neo.domain.usecase.portfolio

import com.projects.neo.data.model.ResponsePortfolios
import com.projects.neo.data.model.ResponseStrings
import com.projects.neo.data.util.Resource
import com.projects.neo.domain.repository.HomeRepository
import com.projects.neo.domain.repository.SplashRepository

class GetPortfoliosUseCase(private val homeRepository: HomeRepository) {
    suspend fun execute(): Resource<ResponsePortfolios>? = homeRepository.getPortfolios()

}