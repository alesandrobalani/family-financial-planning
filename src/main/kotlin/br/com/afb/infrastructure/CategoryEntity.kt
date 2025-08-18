package br.com.afb.infrastructure

import br.com.afb.domain.Category
import br.com.afb.domain.CategoryRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import jakarta.persistence.*

@Entity
@Table(name = "categories")
data class CategoryEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String
) {
    fun toDomain() = Category(id, name)
    companion object {
        fun fromDomain(category: Category) = CategoryEntity(category.id, category.name)
    }
}

interface SpringDataCategoryRepository : JpaRepository<CategoryEntity, Long>

@Repository
class CategoryRepositoryImpl(
    private val jpaRepository: SpringDataCategoryRepository
) : CategoryRepository {
    override fun save(category: Category): Category {
        val entity = CategoryEntity.fromDomain(category)
        return jpaRepository.save(entity).toDomain()
    }
}