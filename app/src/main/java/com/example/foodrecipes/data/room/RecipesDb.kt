package com.example.foodrecipes.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodrecipes.data.dtos.recipe.Recipe
import com.example.foodrecipes.domain.RecipesDbDao

@TypeConverters(value = [RoomTypeConverters::class])
@Database(entities = [Recipe::class], version = 1)
abstract class RecipesDb: RoomDatabase() {
    abstract fun dao(): RecipesDbDao

}