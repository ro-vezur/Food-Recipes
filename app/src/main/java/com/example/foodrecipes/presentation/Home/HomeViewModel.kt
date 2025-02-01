package com.example.foodrecipes.presentation.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipes.data.dtos.Recipe
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

    private val mutableSelectedCategory = MutableLiveData<RecipeCategories>()
    val selectedCategoryLiveData: LiveData<RecipeCategories> = mutableSelectedCategory

    init {
        getAllRecipes()
        setCategory(RecipeCategories.ALL)
    }

    fun setCategory(category: RecipeCategories) = viewModelScope.launch {
        mutableSelectedCategory.value = category
    }

    fun getAllRecipes() = viewModelScope.launch {
        mutableRecipesList.value = recipesRepository.getAllRecipes()
    }

    fun getRecipesWithCategory(category: String) = viewModelScope.launch {
        mutableRecipesList.value = recipesRepository.getRecipesWithCategory(category)
    }

}