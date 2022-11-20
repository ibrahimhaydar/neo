package com.projects.neo.domain.usecase.splash

import com.projects.neo.data.model.ResponseStrings
import com.projects.neo.data.util.Resource
import com.projects.neo.domain.repository.SplashRepository

class GetStringsUseCase(private val splashRepository: SplashRepository) {
    suspend fun execute(): Resource<ResponseStrings>? = splashRepository.getStrings()

}