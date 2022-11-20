package com.projects.neo.data.model


import com.google.gson.annotations.SerializedName

data class ResponsePortfolios(
    @SerializedName("portfolios")
    val portfolios: List<Portfolio> = listOf()
)