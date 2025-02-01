package com.example.foodrecipes.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.foodrecipes.data.dtos.Recipe

@Dao
interface RecipesDbDao {

    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE categories == :category")
    suspend fun getRecipesWithCategory(
        category: String
    ): List<Recipe>

    @Query("SELECT * FROM recipes WHERE id == :id")
    suspend fun getRecipe(
        id: Int
    ): Recipe

    @Query("SELECT * FROM recipes WHERE isFavorite == 1")
    suspend fun gerFavoriteRecipes(): List<Recipe>

    @Query("SELECT * FROM recipes WHERE isFavorite == 1 AND categories = :category")
    suspend fun getFavoriteRecipesWithCategory(
        category: String
    ): List<Recipe>

    @Insert
    suspend fun insertAll(recipes: List<Recipe>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Recipe)


}