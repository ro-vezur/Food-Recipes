package com.example.foodrecipes.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodrecipes.data.enums.RecipeCategories
import com.example.foodrecipes.databinding.ItemRecipeCategoryBinding

class CategoriesAdapter(
    private val savedSelectedItemPos: Int
): RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private val categories = RecipeCategories.entries

    var itemClick: ((RecipeCategories,Int) -> Unit)? = null

    var selectedItemPos = savedSelectedItemPos

    inner class CategoriesViewHolder(
        private val binding: ItemRecipeCategoryBinding,
    ): ViewHolder(binding.root) {

        fun bind(category: RecipeCategories) {
            with(binding) {
                tvCategoryName.text = category.title
            }
        }

        init {
            with(binding) {
                root.setOnClickListener {

                    notifyItemChanged(selectedItemPos)

                    selectedItemPos = adapterPosition
                    itemClick?.invoke(categories[adapterPosition],selectedItemPos)

                    notifyItemChanged(selectedItemPos)
                }
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

        holder.itemView.isSelected = position == selectedItemPos
    }
}