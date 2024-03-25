package com.example.domain.repo

import com.example.core.Resource
import com.example.domain.models.ProductDomain
import com.example.domain.models.ProductsDomain
import kotlinx.coroutines.flow.Flow

interface ProductsRepo {
    fun getProducts(): Flow<Resource<List<ProductsDomain>>>
    fun getProductDetails(id: Int?): Flow<Resource<ProductDomain>>
}