package com.projects.neo.data.model


import com.google.gson.annotations.SerializedName

data class Password(
    @SerializedName("ar")
    val ar: String = "",
    @SerializedName("en")
    val en: String = ""
)