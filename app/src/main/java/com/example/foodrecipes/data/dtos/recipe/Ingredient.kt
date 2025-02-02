package com.example.foodrecipes.data.dtos.recipe

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    val ingredient: String,
    val amount: String,
)