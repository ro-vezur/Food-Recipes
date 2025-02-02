package com.example.foodrecipes.presentation.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodrecipes.data.enums.RecipeCategories
import com.example.foodrecipes.databinding.FragmentFavoritesBinding
import com.example.foodrecipes.presentation.adapters.CategoriesAdapter
import com.example.foodrecipes.presentation.adapters.RecipesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Favorites : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    private val favoritesViewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentFavoritesBinding.inflate(inflater,container,false)

        navController = findNavController()

        setCategoriesRecyclerView()
        observeFavoriteRecipes()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setCategoriesRecyclerView() {
        val categoriesAdapter = CategoriesAdapter(favoritesViewModel.selectedCategoryPosition)

        categoriesAdapter.itemClick = { categoryToSelect, position ->
            favoritesViewModel.selectedCategoryPosition = position

            if(categoryToSelect == RecipeCategories.ALL) {
                favoritesViewModel.getAllFavoriteRecipes()
            } else {
                favoritesViewModel.getFavoriteRecipesWithCategory(categoryToSelect.name)
            }
        }

        binding.rvCategories.layoutManager = LinearLayoutManager(
            requireContext(),LinearLayoutManager.HORIZONTAL,false
        )
        binding.rvCategories.adapter = categoriesAdapter
    }

    private fun observeFavoriteRecipes() {
        favoritesViewModel.favoriteRecipesListLiveData.observe(viewLifecycleOwner) { recipes ->
            val adapter = RecipesAdapter(recipes,requireContext())

            adapter.itemClick = { recipe ->
                val action = FavoritesDirections.favoritesFragmentToDetailedRecipe(recipe.id.toString())
                navController.navigate(action)
            }

            with(binding) {

                if(recipes.isEmpty()) {
                    rvRecipes.visibility = View.GONE
                    tvEmptyMessage.visibility = View.VISIBLE
                } else {
                    rvRecipes.visibility = View.VISIBLE
                    tvEmptyMessage.visibility = View.GONE
                }

                rvRecipes.layoutManager = GridLayoutManager(requireContext(),2)
                rvRecipes.adapter = adapter
            }
        }
    }

}