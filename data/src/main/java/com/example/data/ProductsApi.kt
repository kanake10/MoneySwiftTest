package com.example.data

import com.example.core.Constants.PRODUCTS_ENDPOINT
import com.example.data.dtos.ProductDto
import com.example.data.dtos.ProductsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsApi {
    @GET(PRODUCTS_ENDPOINT)
    suspend fun getProducts(): List<ProductsDto>

    @GET("products/{id}")
    suspend fun getProductDetails(@Path("id")id: Int): ProductDto

}