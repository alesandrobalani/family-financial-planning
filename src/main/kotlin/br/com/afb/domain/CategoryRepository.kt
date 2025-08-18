package br.com.afb.domain

interface CategoryRepository {
    fun save(category: Category): Category
}