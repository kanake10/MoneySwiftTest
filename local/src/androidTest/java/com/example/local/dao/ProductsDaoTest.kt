package com.example.local.dao

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.ProductsDb
import com.example.local.resources.fakeProducts
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ProductsDaoTest {

    private lateinit var database: ProductsDb
    private lateinit var productsDao: ProductsDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder(context, ProductsDb::class.java)
            .allowMainThreadQueries().build()
        productsDao = database.productsDao()
    }

    @After
    fun tearDown() {
        runBlocking {
            productsDao.deleteProducts()
            database.close()
        }
    }

    @Test
    fun insertProducts() {
        runBlocking {
            val productsTestFake = fakeProducts

            val followingTestListUtil = listOf(productsTestFake)
            productsDao.keepProducts(followingTestListUtil)

            val result = productsDao.getProducts()
            Assert.assertEquals(followingTestListUtil, result)
        }
    }

    @Test
    fun deleteProduct() {
        runBlocking {
            productsDao.keepProducts(listOf(fakeProducts))
            productsDao.deleteProducts()
            val appProducts = productsDao.getProducts()
            Truth.assertThat(appProducts).isEmpty()
        }
    }
}