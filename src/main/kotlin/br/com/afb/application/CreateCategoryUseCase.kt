package br.com.afb.application

import br.com.afb.domain.Category
import br.com.afb.domain.CategoryRepository

class CreateCategoryUseCase(private val repository: CategoryRepository) {
    fun execute(category: Category): Category = repository.save(category)
    fun findAll(): List<Category> = repository.findAll()
}