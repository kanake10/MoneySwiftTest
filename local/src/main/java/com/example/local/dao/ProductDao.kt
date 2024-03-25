package com.example.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.entity.ProductEntity

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertSingleProduct(productEntity: ProductEntity)

    @Query("DELETE FROM productentity")
    suspend fun deleteProduct()

    @Query("SELECT * FROM productentity where price =:query")
    suspend fun getSingleProduct(query: Int): ProductEntity?
}