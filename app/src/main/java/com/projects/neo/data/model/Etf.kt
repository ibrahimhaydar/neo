package com.projects.neo.data.model


import com.google.gson.annotations.SerializedName

data class Etf(
    @SerializedName("ar")
    val ar: String = "",
    @SerializedName("en")
    val en: String = ""
)