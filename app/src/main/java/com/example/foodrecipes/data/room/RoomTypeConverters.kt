package com.example.foodrecipes.data.room

import androidx.room.TypeConverter
import com.example.foodrecipes.data.dtos.recipe.Ingredient
import com.example.foodrecipes.data.enums.RecipeCategories
import com.google.gson.Gson
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class RoomTypeConverters {

    @TypeConverter
    fun convertStringListToJson(list: List<String>): String = Json.encodeToString(list)
    @TypeConverter
    fun convertJsonToStringList(json: String): List<String> = Json.decodeFromString(json)

    @TypeConverter
    fun convertIngredientsListToJson(ingredientsList: List<Ingredient>): String = Json.encodeToString(ingredientsList)
    @TypeConverter
    fun convertJsonToIngredientsList(json: String): List<Ingredient> = Json.decodeFromString(json)

    @TypeConverter
    fun convertCategoriesListToJson(list: List<RecipeCategories>) = Json.encodeToString(list)
    @TypeConverter
    fun convertJsonToCategoriesList(json: String): List<RecipeCategories> = Json.decodeFromString(json)

}