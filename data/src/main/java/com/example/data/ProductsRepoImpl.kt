package com.example.data


import com.example.core.Resource
import com.example.data.dtos.toDomain
import com.example.data.dtos.toEntity
import com.example.domain.models.ProductDomain
import com.example.domain.models.ProductsDomain
import com.example.domain.repo.ProductsRepo
import com.example.local.dao.ProductDao
import com.example.local.dao.ProductsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductsRepoImpl @Inject constructor(
    private val productsApi: ProductsApi,
    private val productsDao: ProductsDao,
    private val productDao: ProductDao
) :ProductsRepo {
    override fun getProducts(): Flow<Resource<List<ProductsDomain>>> = flow {
        emit(Resource.Loading())

        val allProducts = productsDao.getProducts()
        emit(Resource.Loading(data = allProducts.map { it.toDomain() }))

        try {
            val apiResponseForAll = productsApi.getProducts()
            productsDao.deleteProducts()
            productsDao.keepProducts(apiResponseForAll.map { it.toEntity() })
        } catch (exception: IOException) {
            emit(
                Resource.Error(
                    message = "Connection Lost",
                    data = allProducts.map { it.toDomain() }
                )
            )
        } catch (exception: HttpException) {
            emit(
                Resource.Error(
                    message = exception.message(),
                    data = allProducts.map { it.toDomain() }
                )
            )
        }
        val allData = productsDao.getProducts().map { it.toDomain() }
        emit(Resource.Success(allData))
    }
    override fun getProductDetails(id: Int?): Flow<Resource<ProductDomain>> = flow {
        emit(Resource.Loading())

        val productEntity = productDao.getSingleProduct(id ?: 0) // assuming default id if null
        val productDomain = productEntity?.toDomain()

        emit(Resource.Loading(data = productDomain))

        try {
            val apiResponse = productsApi.getProductDetails(id!!)
            val productFromApi = apiResponse.toEntity()

            productDao.insertSingleProduct(productFromApi)
            emit(Resource.Success(productFromApi.toDomain()))
        } catch (exception: IOException) {
            emit(Resource.Error(message = "Connection Lost", data = productDomain))
        } catch (exception: HttpException) {
            emit(Resource.Error(message = exception.message(), data = productDomain))
        }
    }
}