package com.example.foodrecipes.presentation.Home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.foodrecipes.data.enums.RecipeCategories
import com.example.foodrecipes.databinding.FragmentHomeBinding
import com.example.foodrecipes.presentation.Home.Adapters.CategoriesAdapter
import com.example.foodrecipes.presentation.Home.Adapters.RecipesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        observeRecipeList()
        observeSelectedCategory()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeRecipeList() {
        homeViewModel.recipesListLiveData.observe(viewLifecycleOwner) { recipes ->

            binding.rvRecipes.layoutManager = GridLayoutManager(requireContext(),2)
            binding.rvRecipes.adapter = RecipesAdapter(recipes,requireContext())

        }
    }

    private fun observeSelectedCategory() {
        homeViewModel.selectedCategoryLiveData.observe(viewLifecycleOwner) { category ->

            val adapter = CategoriesAdapter(category,requireContext())

            adapter.itemClick = { categoryToSelect ->
                Log.d("category",categoryToSelect.toString())
                homeViewModel.setCategory(categoryToSelect)
                if(categoryToSelect == RecipeCategories.ALL) {
                    homeViewModel.getAllRecipes()
                } else {
                    homeViewModel.getRecipesWithCategory(categoryToSelect.name)
                }
            }

            binding.rvCategories.layoutManager = LinearLayoutManager(
                requireContext(),LinearLayoutManager.HORIZONTAL,false
            )

            binding.rvCategories.adapter = adapter
        }
    }
}