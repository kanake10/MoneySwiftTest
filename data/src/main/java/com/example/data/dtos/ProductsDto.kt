package com.example.data.dtos


import com.example.domain.models.ProductsDomain
import com.example.entity.ProductsEntity

data class ProductsDto(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)

internal fun ProductsDto.toEntity(): ProductsEntity {
    return ProductsEntity(
        this.image,
        this.id,
        this.description,
        this.price,
        this.title
    )
}

internal fun ProductsEntity.toDomain(): ProductsDomain {
    return ProductsDomain(
        this.image,
        this.id,
        this.description,
        this.price,
        this.title
    )
}

