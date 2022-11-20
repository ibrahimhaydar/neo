package com.projects.neo.data.model


import com.google.gson.annotations.SerializedName

data class ResponseStrings(
    @SerializedName("strings")
    val strings: Strings = Strings()
)