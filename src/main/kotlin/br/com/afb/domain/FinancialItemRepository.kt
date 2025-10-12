package br.com.afb.domain

interface FinancialItemRepository {
    fun save(financialItem: FinancialItem): FinancialItem
    fun findAll(): List<FinancialItem>
}