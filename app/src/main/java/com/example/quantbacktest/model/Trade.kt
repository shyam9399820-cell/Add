package com.example.quantbacktest.model

data class Trade(
    val tradeId: Int,
    val entryDateTime: String,
    val entryPrice: Double,
    val stopLoss: Double,
    val target: Double,
    val exitDateTime: String,
    val exitPrice: Double,
    val pnl: Double,
    val rrr: Double
)
