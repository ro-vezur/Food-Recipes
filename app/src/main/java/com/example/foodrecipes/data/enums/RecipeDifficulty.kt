package com.example.foodrecipes.data.enums

import android.graphics.Color

enum class RecipeDifficulty(val difficulty: String, val colorId: Int) {
    EASY("Easy",Color.GREEN),
    MEDIUM("Medium",Color.YELLOW),
    HARD("Hard",Color.RED)
}