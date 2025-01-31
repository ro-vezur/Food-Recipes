package com.example.foodrecipes.di

import com.example.foodrecipes.data.repositories.RecipesRepository
import com.example.foodrecipes.domain.RecipesDbDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesDi {

    @Provides
    @Singleton
    fun provideRecipesRepository(recipesDbDao: RecipesDbDao): RecipesRepository {
        return RecipesRepository(recipesDbDao)
    }

}