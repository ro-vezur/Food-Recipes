package com.example.foodrecipes.data.dtos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodrecipes.RECIPES_DB_NAME
import com.example.foodrecipes.data.enums.RecipeCategories
import com.example.foodrecipes.data.enums.RecipeDifficulty

@Entity(tableName = RECIPES_DB_NAME)
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val recipeName: String,
    val image: String,
    val cookingTime: Int,
    val ingredients: List<String>,
    val instructions: List<String>,
    val difficulty: RecipeDifficulty,
    val categories: RecipeCategories,
    val isFavorite: Boolean = false,
)
