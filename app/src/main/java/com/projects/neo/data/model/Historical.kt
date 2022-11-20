package com.projects.neo.data.model


import com.google.gson.annotations.SerializedName

data class Historical(
    @SerializedName("benchmarkValue")
    val benchmarkValue: Int = 0,
    @SerializedName("date")
    val date: String = "",
    @SerializedName("date_js")
    val dateJs: String = "",
    @SerializedName("smartWealthValue")
    val smartWealthValue: Int = 0
)