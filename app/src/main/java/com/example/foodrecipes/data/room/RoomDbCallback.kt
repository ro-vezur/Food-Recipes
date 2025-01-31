package com.example.foodrecipes.data.room

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.foodrecipes.data.dtos.Recipe
import com.example.foodrecipes.domain.RecipesDbDao
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class RoomDbCallback @Inject constructor(
    private val context: Context,
    private val provider: Provider<RecipesDbDao>
): RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        CoroutineScope(Dispatchers.IO).launch {
            populateDatabase(provider.get(),context)
        }
    }

    private suspend fun populateDatabase(recipeDao: RecipesDbDao, context: Context) {
        val jsonString = context.assets.open("recipes.json").bufferedReader().use { it.readText() }
        val recipeList: List<Recipe> = Gson().fromJson(jsonString, object : TypeToken<List<Recipe>>() {}.type)

        recipeDao.insertAll(recipeList)
    }

}