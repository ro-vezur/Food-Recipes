package com.example.foodrecipes.data.repositories

import com.example.foodrecipes.data.dtos.recipe.Recipe
import com.example.foodrecipes.domain.RecipesDbDao
import javax.inject.Inject

class RecipesRepository @Inject constructor(
    private val recipesDbDao: RecipesDbDao
) {
    suspend fun getAllRecipes() = recipesDbDao.getAllRecipes()

    suspend fun getRecipesWithCategory(category: String) =
        recipesDbDao.getRecipesWithCategory("%${category}%")

    suspend fun getRecipe(id: Int) = recipesDbDao.getRecipe(id)

    suspend fun getFavoriteRecipes() = recipesDbDao.gerFavoriteRecipes()

    suspend fun getFavoriteRecipesWithCategory(category: String) =
        recipesDbDao.getFavoriteRecipesWithCategory("%${category}%")

    suspend fun insertRecipe(recipe: Recipe) = recipesDbDao.insertRecipe(recipe)
}