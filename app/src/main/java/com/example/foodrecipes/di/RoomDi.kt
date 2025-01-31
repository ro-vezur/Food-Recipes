package com.example.foodrecipes.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodrecipes.RECIPES_DB_NAME
import com.example.foodrecipes.data.room.RecipesDb
import com.example.foodrecipes.data.room.RoomDbCallback
import com.example.foodrecipes.domain.RecipesDbDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDi {

    @Provides
    @Singleton
    fun provideRoomDb(@ApplicationContext context: Context,provider: Provider<RecipesDbDao>): RecipesDb {
        return Room.databaseBuilder(
            context = context,
            klass = RecipesDb::class.java,
            name = RECIPES_DB_NAME
        )
            .addCallback(RoomDbCallback(context,provider))
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(recipesDb: RecipesDb): RecipesDbDao {
        return recipesDb.dao()
    }
}