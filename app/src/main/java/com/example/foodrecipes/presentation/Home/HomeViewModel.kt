package com.example.foodrecipes.presentation.Home

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
class HomeViewModel @Inject constructor(
    private val recipesRepository: RecipesRepository
): ViewModel() {

    private val mutableRecipesList = MutableLiveData<List<Recipe>>()
    val recipesListLiveData: LiveData<List<Recipe>> = mutableRecipesList

    var selectedCategoryPosition = 0

    init {
        getAllRecipes()
    }

    fun getAllRecipes() = viewModelScope.launch {
        mutableRecipesList.value = recipesRepository.getAllRecipes()
    }

    fun getRecipesWithCategory(category: String) = viewModelScope.launch {
        mutableRecipesList.value = recipesRepository.getRecipesWithCategory(category)
    }

}