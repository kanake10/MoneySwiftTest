package com.example.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.entity.ProductsEntity

@Dao
interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun keepProducts(products: List<ProductsEntity>)

    @Query("DELETE FROM productsentity")
    suspend fun deleteProducts()

    @Query("SELECT * FROM productsentity")
    suspend fun getProducts(): List<ProductsEntity>
}