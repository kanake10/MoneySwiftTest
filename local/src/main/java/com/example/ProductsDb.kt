package com.example

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.entity.ProductEntity
import com.example.core.Constants.DB_NAME
import com.example.entity.ProductsEntity
import com.example.local.dao.ProductDao
import com.example.local.dao.ProductsDao

@TypeConverters(Converters::class)
@Database(
    entities = [ProductsEntity::class, ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ProductsDb : RoomDatabase(){

    companion object {
        fun getInstance(context: Context): ProductsDb {
            return Room.databaseBuilder(context, ProductsDb::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun productsDao(): ProductsDao
    abstract fun productDao(): ProductDao
}