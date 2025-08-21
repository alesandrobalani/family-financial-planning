package br.com.afb.api

import br.com.afb.application.CreateCategoryUseCase
import br.com.afb.domain.Category
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid

@RestController
@RequestMapping("/categories")
class CategoryController(
    private val createCategoryUseCase: CreateCategoryUseCase
) {
    @PostMapping
    fun create(@RequestBody @Valid request: CategoryRequest): Category {
        val category = Category(name = request.name)
        return createCategoryUseCase.execute(category)
    }
}

data class CategoryRequest(
    val id: Long? = null,
    @field:Size(max=70, message = "The category name must be minor then 70 characters")
    val name: String)