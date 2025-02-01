package com.example.foodrecipes.presentation.detailedRecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.foodrecipes.R
import com.example.foodrecipes.databinding.FragmentDetailedRecipeFragmentBinding
import com.example.foodrecipes.presentation.detailedRecipe.adapters.IngredientsAdapter
import com.example.foodrecipes.presentation.detailedRecipe.adapters.InstructionsAdapter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.withCreationCallback

@AndroidEntryPoint
class DetailedRecipeFragment : Fragment() {

    private var _binding: FragmentDetailedRecipeFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var recipeId: String

    private val detailedRecipeViewModel: DetailedRecipeViewModel by viewModels(
        extrasProducer = {
            defaultViewModelCreationExtras.withCreationCallback<DetailedRecipeViewModel.Factory> { factory ->
                factory.create(recipeId.toInt())
            }
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentDetailedRecipeFragmentBinding.inflate(
            inflater,container,false
        )

        val args: DetailedRecipeFragmentArgs by navArgs()
        recipeId = args.id

        observeRecipe()

        binding.fabFavorite.setOnClickListener{
            detailedRecipeViewModel.addRecipeToFavorite()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeRecipe() {
        detailedRecipeViewModel.recipeLiveData.observe(viewLifecycleOwner) { recipe ->

            with(binding) {
                val recipeImage = requireContext().resources.getIdentifier(
                    recipe.image,"drawable",requireContext().packageName
                )

                Glide.with(imageRecipe.context)
                    .load(recipeImage)
                    .into(imageRecipe)

                tvRecipeName.text = recipe.recipeName
                tvCookTime.text = "${recipe.cookingTime} min"

                rvIngredients.layoutManager = LinearLayoutManager(requireContext())
                rvIngredients.adapter = IngredientsAdapter(recipe.ingredients)

                rvInstruction.layoutManager = LinearLayoutManager(requireContext())
                rvInstruction.adapter = InstructionsAdapter(recipe.instructions)

                if(recipe.isFavorite) {
                    fabFavorite.setImageResource(R.drawable.ic_filled_favorite)
                } else {
                    fabFavorite.setImageResource(R.drawable.ic_outline_favorite)
                }

            }

        }
    }

}