package com.example.foodrecipes.data.repositories

import com.example.foodrecipes.domain.RecipesDbDao
import javax.inject.Inject

class RecipesRepository @Inject constructor(
    private val recipesDbDao: RecipesDbDao
) {
    suspend fun getAllRecipes() = recipesDbDao.getAllRecipes()

    suspend fun getRecipesWithCategory(category: String) = recipesDbDao.getRecipesWithCategory(category)
}