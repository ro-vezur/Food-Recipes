package com.example.foodrecipes.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.foodrecipes.data.dtos.Recipe

@Dao
interface RecipesDbDao {

    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE categories == :category")
    suspend fun getRecipesWithCategory(
        category: String
    ): List<Recipe>

    @Insert
    suspend fun insertAll(recipes: List<Recipe>)

}