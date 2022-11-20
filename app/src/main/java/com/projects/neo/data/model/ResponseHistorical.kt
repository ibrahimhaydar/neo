package com.projects.neo.data.model


import com.google.gson.annotations.SerializedName

data class ResponseHistorical(
    @SerializedName("historical")
    val historical: List<Historical> = listOf()
)