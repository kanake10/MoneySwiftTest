package com.example.domain.usecase

import com.example.core.Resource
import com.example.domain.models.ProductDomain
import com.example.domain.models.ProductsDomain
import com.example.domain.repo.ProductsRepo
import com.example.domain.usecases.ProductUseCase
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ProductUseCaseTest {

    companion object {
        fun mockProductsRepo(
            flowReturn: Flow<Resource<ProductDomain>>
        ) = object : ProductsRepo {
            override fun getProducts(): Flow<Resource<List<ProductsDomain>>> {
                // Implement mock for getProducts if needed for other tests
                throw NotImplementedError("getProducts is not implemented for this test.")
            }
            override fun getProductDetails(id: Int?): Flow<Resource<ProductDomain>> =
                flowReturn
        }
    }

    @Test
    fun `Get product details starts with loading RETURNS Resource Loading`() = runBlocking {
        val product = mockk<ProductDomain>()
        val id = 1

        val productsRepo = mockProductsRepo(flow {
            emit(Resource.Loading())
            emit(Resource.Success(product))
        })

        val result = ProductUseCase(productsRepo)(id).first()

        assertTrue(result is Resource.Loading)
    }

    @Test
    fun `Get product details success result RETURNS Resource + Data`() = runBlocking {
        val product = mockk<ProductDomain>()
        val id = 1

        val productsRepo = mockProductsRepo(flow {
            emit(Resource.Loading())
            emit(Resource.Success(product))
        })

        val result = ProductUseCase(productsRepo)(id).last()

        assertTrue(result is Resource.Success && result.data == product)
    }
}