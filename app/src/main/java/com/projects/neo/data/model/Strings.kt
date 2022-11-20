package com.projects.neo.data.model


import com.google.gson.annotations.SerializedName

data class Strings(
    @SerializedName("balance")
    val balance: Balance = Balance(),
    @SerializedName("date")
    val date: Date = Date(),
    @SerializedName("earnings")
    val earnings: Earnings = Earnings(),
    @SerializedName("email")
    val email: Email = Email(),
    @SerializedName("etf")
    val etf: Etf = Etf(),
    @SerializedName("home")
    val home: Home = Home(),
    @SerializedName("investment_type")
    val investmentType: InvestmentType = InvestmentType(),
    @SerializedName("login")
    val login: Login = Login(),
    @SerializedName("mmf")
    val mmf: Mmf = Mmf(),
    @SerializedName("password")
    val password: Password = Password(),
    @SerializedName("risk_score")
    val riskScore: RiskScore = RiskScore()
)