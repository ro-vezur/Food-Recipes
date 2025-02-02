package com.example.foodrecipes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.foodrecipes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        val navHostFragment = binding.navHostFragmentContainer.getFragment<NavHostFragment>()
        navController = navHostFragment.findNavController()

        //  applicationContext.deleteDatabase(RECIPES_DB_NAME)


        binding.bottomNavigationView.setupWithNavController(navController)

        setBottomNavigation()
    }

    private fun setBottomNavigation() {

        val bottomNavigationView = binding.bottomNavigationView

        bottomNavigationView.setOnItemSelectedListener { item ->
            if(item.itemId != bottomNavigationView.selectedItemId) {
                navController.popBackStack(item.itemId,inclusive = true,saveState = false)
                navController.navigate(item.itemId)
            }

            true
        }
    }
}