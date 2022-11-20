package com.projects.neo.data.api

import com.projects.neo.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("historical")
    suspend fun getHistorical(
    ): Response<ResponseHistorical>

    @GET("strings")
    suspend fun getStrings(
    ): Response<ResponseStrings>

    @GET("portfolios")
    suspend fun getPortfolios(
    ): Response<ResponsePortfolios>

}