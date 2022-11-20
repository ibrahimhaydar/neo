package com.projects.neo.data.model


import com.google.gson.annotations.SerializedName

data class Portfolio(
    @SerializedName("balance")
    val balance: Int = 0,
    @SerializedName("created_at")
    val createdAt: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("investment_type")
    val investmentType: String = "",
    @SerializedName("latest_daily_earning")
    val latestDailyEarning: Int = 0,
    @SerializedName("modified_risk_score")
    val modifiedRiskScore: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("total_earnings")
    val totalEarnings: Int = 0
):java.io.Serializable