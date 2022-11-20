package com.projects.neo.domain.repository

import com.projects.neo.data.model.ResponsePortfolios
import com.projects.neo.data.model.ResponseStrings
import com.projects.neo.data.util.Resource

interface HomeRepository {
    suspend fun getPortfolios(): Resource<ResponsePortfolios>?
}