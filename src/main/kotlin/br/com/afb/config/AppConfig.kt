package br.com.afb.config

import br.com.afb.application.CreateCategoryUseCase
import br.com.afb.domain.CategoryRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Bean
    fun createCategoryUseCase(repository: CategoryRepository) = CreateCategoryUseCase(repository)
}