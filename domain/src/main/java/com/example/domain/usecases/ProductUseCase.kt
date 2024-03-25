package com.example.domain.usecases

import com.example.core.Resource
import com.example.domain.models.ProductDomain
import com.example.domain.repo.ProductsRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductUseCase @Inject constructor(private val productsRepo: ProductsRepo) {

    operator fun invoke(id: Int): Flow<Resource<ProductDomain>> {
        return productsRepo.getProductDetails(id)
    }
}