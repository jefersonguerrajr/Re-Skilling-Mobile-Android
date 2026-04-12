package dev.jefersonguerrajr.controlefinanceiro.data.model

data class Transaction(
    val title: String,
    val description: String,
    val amount: Double,
    val type: String // "revenue" or "expenses"
)
