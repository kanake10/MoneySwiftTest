package com.example.domain.usecase

import com.example.core.Resource
import com.example.domain.models.ProductDomain
import com.example.domain.models.ProductsDomain
import com.example.domain.repo.ProductsRepo
import com.example.domain.usecases.ProductsUseCase
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ProductsUseCaseTest {

    companion object {
        fun mockProductsRepo(
            flowReturn: Flow<Resource<List<ProductsDomain>>>
        ) = object : ProductsRepo {
            override fun getProducts(): Flow<Resource<List<ProductsDomain>>> = flowReturn
            override fun getProductDetails(id: Int?): Flow<Resource<ProductDomain>> {
                // Implement mock for getProductDetails if needed for other tests
                throw NotImplementedError("getProductDetails is not implemented for this test.")
            }
        }
    }

    @Test
    fun `Get products starts with loading RETURNS Resource Loading`() = runBlocking {
        val products = mockk<List<ProductsDomain>>()

        val productsRepo = mockProductsRepo(flow {
            emit(Resource.Loading())
            emit(Resource.Success(products))
        })

        val result = ProductsUseCase(productsRepo)().first()

        assertTrue(result is Resource.Loading)
    }

    @Test
    fun `Get products success result RETURNS Resource + Data`() = runBlocking {
        val products = mockk<List<ProductsDomain>>()

        val productsRepo = mockProductsRepo(flow {
            emit(Resource.Loading())
            emit(Resource.Success(products))
        })

        val result = ProductsUseCase(productsRepo)().last()

        assertTrue(result is Resource.Success && result.data == products)
    }
}