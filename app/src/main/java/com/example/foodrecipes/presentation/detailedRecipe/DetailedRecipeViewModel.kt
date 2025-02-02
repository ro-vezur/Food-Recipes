package com.example.foodrecipes.presentation.detailedRecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipes.data.dtos.recipe.Recipe
import com.example.foodrecipes.data.repositories.RecipesRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = DetailedRecipeViewModel.Factory::class)
class DetailedRecipeViewModel @AssistedInject constructor(
    @Assisted val id: Int,
    private val recipesRepository: RecipesRepository
): ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(id: Int): DetailedRecipeViewModel
    }

    private val mutableRecipe = MutableLiveData<Recipe>()
    val recipeLiveData: LiveData<Recipe> = mutableRecipe

    init {
        setRecipe()
    }

    fun setRecipe() = viewModelScope.launch {
        mutableRecipe.value = recipesRepository.getRecipe(id)
    }

    fun addRecipeToFavorite() = viewModelScope.launch {
        val recipe = recipesRepository.getRecipe(id)

        recipesRepository.insertRecipe(recipe.copy(isFavorite = !recipe.isFavorite))
        setRecipe()
    }
}