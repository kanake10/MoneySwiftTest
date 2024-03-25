package com.example.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    val image:String,
    @PrimaryKey
    val id : Int,
    val description :String,
    val price : Double,
    val title :String
)
