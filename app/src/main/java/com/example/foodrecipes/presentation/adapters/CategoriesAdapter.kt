package com.example.foodrecipes.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodrecipes.R
import com.example.foodrecipes.data.enums.RecipeCategories
import com.example.foodrecipes.databinding.ItemRecipeCategoryBinding

class CategoriesAdapter(
    private val selectedCategory: RecipeCategories,
    private val context: Context,
): RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private val categories = RecipeCategories.entries

    var itemClick: ((RecipeCategories) -> Unit)? = null

    inner class CategoriesViewHolder(
        private val binding: ItemRecipeCategoryBinding,
    ): ViewHolder(binding.root) {

        fun bind(category: RecipeCategories) {
            with(binding) {
                tvCategoryName.text = category.title
                if(category == selectedCategory) {
                    tvCategoryName.setTextColor(context.getColor(R.color.primary))
                    root.setBackgroundResource(R.drawable.bg_selected_category)
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                itemClick?.invoke(categories[adapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {

        val binding = ItemRecipeCategoryBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )

        return CategoriesViewHolder(binding)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
    }
}