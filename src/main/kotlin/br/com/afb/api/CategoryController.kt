package br.com.afb.api

import br.com.afb.application.CreateCategoryUseCase
import br.com.afb.domain.Category
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class CategoryController(
    private val createCategoryUseCase: CreateCategoryUseCase
) {
    @PostMapping
    fun create(@RequestBody request: CategoryRequest): Category {
        val category = Category(name = request.name)
        return createCategoryUseCase.execute(category)
    }
}

data class CategoryRequest(val name: String)