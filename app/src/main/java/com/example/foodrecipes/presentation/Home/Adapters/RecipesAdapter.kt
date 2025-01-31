package com.example.foodrecipes.presentation.Home.Adapters

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.foodrecipes.data.dtos.Recipe
import com.example.foodrecipes.databinding.ItemRecipeCardBinding
import java.util.zip.Inflater

class RecipesAdapter(
    private val recipesList: List<Recipe>,
    private val context: Context
): RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {

    inner class RecipesViewHolder(
        private val binding: ItemRecipeCardBinding,
    ): ViewHolder(binding.root) {
        fun bind(recipe: Recipe) {

            val foodImageId = context.resources.getIdentifier(
                recipe.image,"drawable",context.packageName
            )

            with(binding) {
                Glide.with(root.context)
                    .load(foodImageId)
                    .into(ivRecipeImage)
                tvRecipeName.text = recipe.recipeName
                tvRecipeCategory.text = recipe.categories.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val binding = ItemRecipeCardBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )

        return RecipesViewHolder(binding)
    }

    override fun getItemCount(): Int = recipesList.size

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val recipe = recipesList[position]

       holder.bind(recipe)
    }
}