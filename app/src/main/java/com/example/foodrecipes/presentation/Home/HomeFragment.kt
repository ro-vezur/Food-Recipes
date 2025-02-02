package com.example.foodrecipes.presentation.Home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipes.data.enums.RecipeCategories
import com.example.foodrecipes.databinding.FragmentHomeBinding
import com.example.foodrecipes.presentation.adapters.CategoriesAdapter
import com.example.foodrecipes.presentation.adapters.RecipesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        navController = findNavController()

        observeRecipeList()
        setCategoriesRecyclerView()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeRecipeList() {
        homeViewModel.recipesListLiveData.observe(viewLifecycleOwner) { recipes ->
            val adapter = RecipesAdapter(recipes,requireContext())

            adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

            adapter.itemClick = { recipe ->
                val action = HomeFragmentDirections.homeFragmentToDetailedRecipe(recipe.id.toString())
                navController.navigate(action)
            }

            binding.rvRecipes.adapter = adapter
        }
    }


    private fun setCategoriesRecyclerView() {
        val categoriesAdapter = CategoriesAdapter( homeViewModel.selectedCategoryPosition)

        categoriesAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        categoriesAdapter.itemClick = { categoryToSelect, position ->
            homeViewModel.selectedCategoryPosition = position

            if(categoryToSelect == RecipeCategories.ALL) {
                homeViewModel.getAllRecipes()
            } else {
                homeViewModel.getRecipesWithCategory(categoryToSelect.name)
            }
        }

        binding.rvCategories.layoutManager = LinearLayoutManager(
            requireContext(),LinearLayoutManager.HORIZONTAL,false
        )
        binding.rvCategories.adapter = categoriesAdapter
    }

}