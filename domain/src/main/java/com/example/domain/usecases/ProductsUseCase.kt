package com.example.domain.usecases

import com.example.core.Resource
import com.example.domain.models.ProductsDomain
import com.example.domain.repo.ProductsRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsUseCase @Inject constructor(private val productsRepo: ProductsRepo) {

    operator fun invoke(): Flow<Resource<List<ProductsDomain>>> {
        return productsRepo.getProducts()
    }
}