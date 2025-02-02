package com.example.foodrecipes.presentation.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipes.data.dtos.recipe.Recipe
import com.example.foodrecipes.data.enums.RecipeCategories
import com.example.foodrecipes.data.repositories.RecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val recipesRepository: RecipesRepository
): ViewModel() {

    private val mutableFavoriteRecipesList = MutableLiveData<List<Recipe>>()
    val favoriteRecipesListLiveData: LiveData<List<Recipe>> = mutableFavoriteRecipesList

    var selectedCategoryPosition = 0

    init {
        getAllFavoriteRecipes()
    }

    fun getAllFavoriteRecipes() = viewModelScope.launch {
        mutableFavoriteRecipesList.value = recipesRepository.getFavoriteRecipes()
    }

    fun getFavoriteRecipesWithCategory(category: String) = viewModelScope.launch {
        mutableFavoriteRecipesList.value = recipesRepository.getFavoriteRecipesWithCategory(category)
    }

}