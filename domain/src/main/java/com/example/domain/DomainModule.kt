package com.example.domain

import com.example.domain.repo.ProductsRepo
import com.example.domain.usecases.AllUseCases
import com.example.domain.usecases.ProductUseCase
import com.example.domain.usecases.ProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideUseCases(repository: ProductsRepo): AllUseCases {
        return AllUseCases(
            productsUseCase = ProductsUseCase(repository),
            productUseCase = ProductUseCase(repository)
        )
    }
}