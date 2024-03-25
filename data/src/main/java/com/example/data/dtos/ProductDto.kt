package com.example.data.dtos

import com.example.domain.models.ProductDomain
import com.example.entity.ProductEntity


data class ProductDto(
    val image:String,
    val id : Int,
    val description :String,
    val price : Double,
    val title :String
)


internal fun ProductDto.toEntity(): ProductEntity {
    return ProductEntity(
        this.image,
        this.id,
        this.description,
        this.price,
        this.title
    )
}

internal fun ProductEntity.toDomain(): ProductDomain {
    return ProductDomain(
        this.image,
        this.id,
        this.description,
        this.price,
        this.title
    )
}