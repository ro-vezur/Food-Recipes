package com.example.foodrecipes.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodrecipes.RECIPES_DB_NAME
import com.example.foodrecipes.data.dtos.Recipe
import com.example.foodrecipes.domain.RecipesDbDao
import dagger.hilt.android.qualifiers.ApplicationContext

@TypeConverters(value = [RoomTypeConverters::class])
@Database(entities = [Recipe::class], version = 1)
abstract class RecipesDb: RoomDatabase() {
    abstract fun dao(): RecipesDbDao

}