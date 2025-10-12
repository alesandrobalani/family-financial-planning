package br.com.afb.infrastructure

import br.com.afb.domain.Category
import br.com.afb.domain.CategoryRepository
import br.com.afb.domain.FinancialItem
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Entity
@Table(name = "financial_item")
data class FinancialItemEntity(
    @Id @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    val category: CategoryEntity,

    @Column(nullable = false, length = 70)
    val name: String,

    @Column(nullable = false, length = 70, name = "regex_name")
    val regexName: String
){
    fun toDomain() = Category(id, name)
    companion object {
        fun fromDomain(financialItem: FinancialItem) =
            FinancialItemEntity(
                UUID.randomUUID(),
                category = financialItem.category.let { CategoryEntity.fromDomain(it) },
                name=financialItem.name,
                financialItem.name.replace("[^a-zA-Z0-9]".toRegex(), "")
            )
    }
}

interface SpringDataFinancialItemRepository : JpaRepository<FinancialItemEntity, Long>

@Repository
class CategoryRepositoryImpl(
    private val jpaRepository: SpringDataFinancialItemRepository
) : FinancialItemRepository {
    override fun save(financialItem: FinancialItem): FinancialItem {
        val entity = FinancialItemEntity.fromDomain(financialItem)
        return jpaRepository.save(entity).toDomain()
    }
    override fun findAll(): List<FinancialItem> {
        return jpaRepository.findAll().map { it.toDomain() }
    }
}