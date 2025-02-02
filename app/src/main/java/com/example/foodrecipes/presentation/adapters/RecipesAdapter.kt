package com.example.foodrecipes.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.foodrecipes.R
import com.example.foodrecipes.data.dtos.recipe.Recipe
import com.example.foodrecipes.databinding.ItemRecipeCardBinding

class RecipesAdapter(
    private val recipesList: List<Recipe>,
    private val context: Context
): RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {

    var itemClick: ((Recipe) -> Unit)? = null

    inner class RecipesViewHolder(
        private val binding: ItemRecipeCardBinding,
    ): ViewHolder(binding.root) {
        fun bind(recipe: Recipe) {

            val foodImageId = context.resources.getIdentifier(
                recipe.image,"drawable",context.packageName
            )


            with(binding) {

                if(recipe.isFavorite) {
                    icFavorite.setImageResource(R.drawable.ic_filled_favorite)
                } else {
                    icFavorite.setImageResource(R.drawable.ic_outline_favorite)
                }

                Glide.with(root.context)
                    .load(foodImageId)
                    .into(ivRecipeImage)

                tvRecipeName.text = recipe.recipeName
                tvRecipeCategory.text = recipe.categories.joinToString { it.title }
            }
        }

        init {
            binding.root.setOnClickListener {
                itemClick?.invoke(recipesList[adapterPosition])
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