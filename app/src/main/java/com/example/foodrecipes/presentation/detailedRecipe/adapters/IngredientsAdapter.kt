package com.example.foodrecipes.presentation.detailedRecipe.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodrecipes.data.dtos.recipe.Ingredient
import com.example.foodrecipes.databinding.ItemIngredientBinding

class IngredientsAdapter(
    private val ingredients: List<Ingredient>
): RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {

    inner class IngredientsViewHolder(private val binding: ItemIngredientBinding): ViewHolder(binding.root) {
        fun bind(ingredient: Ingredient,position: Int) {
            with(binding) {
                tvIngredient.text = "${ingredient.ingredient} ${ingredient.amount} (${position + 1})"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val binding = ItemIngredientBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )

        return IngredientsViewHolder(binding)
    }

    override fun getItemCount(): Int = ingredients.size

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val ingredient = ingredients[position]

        holder.bind(ingredient,position)
    }

}