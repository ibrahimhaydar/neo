package com.projects.neo.data.model


import com.google.gson.annotations.SerializedName

data class RiskScore(
    @SerializedName("ar")
    val ar: String = "",
    @SerializedName("en")
    val en: String = ""
)