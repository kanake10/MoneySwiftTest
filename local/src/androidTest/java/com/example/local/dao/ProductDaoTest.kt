package com.example.local.dao

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.ProductsDb
import com.example.local.resources.fakeProduct
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class ProductDaoTest {

    private lateinit var database: ProductsDb
    private lateinit var productDao: ProductDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder(context, ProductsDb::class.java)
            .allowMainThreadQueries().build()
        productDao = database.productDao()
    }

    @After
    fun tearDown() {
        runBlocking {
            productDao.deleteProduct()
            database.close()
        }
    }


    @Test
    fun `deleteProduct`() {
        runBlocking {
            productDao.insertSingleProduct(fakeProduct)
            productDao.deleteProduct()
            val userProfile = productDao.getSingleProduct(1)

            Truth.assertThat(userProfile).isNull()
        }
    }

}