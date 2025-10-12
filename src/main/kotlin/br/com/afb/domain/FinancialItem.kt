package br.com.afb.domain

import java.util.UUID

data class FinancialItem(
    val id: UUID? = null,
    val category: Category,
    val name: String,
)