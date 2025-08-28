package br.com.afb.api

import br.com.afb.application.CreateCategoryUseCase
import br.com.afb.domain.Category
import jakarta.validation.Valid
import jakarta.validation.constraints.Size
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/categories")
class CategoryController(
    private val createCategoryUseCase: CreateCategoryUseCase
) {
    @PostMapping
    fun create(@RequestBody @Valid request: CategoryRequest): Category {
        val category = Category(name = request.name)
        return createCategoryUseCase.execute(category)
    }

    @GetMapping
    fun getAll(): List<Category> {
        return createCategoryUseCase.findAll()
    }
}

data class CategoryRequest(
    @field:Size(max=70, message = "The category name must be minor then 70 characters")
    val name: String)