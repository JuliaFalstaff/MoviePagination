package com.example.moviepagination.presentation


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.moviepagination.R
import com.example.moviepagination.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    val navController by lazy {  Navigation.findNavController(this, R.id.container) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        binding.bottomAppNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_list -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.container, MovieListFragment.newInstance())
                            .commit()
                    true
                }
                R.id.menu_search -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.container, SearchFragment.newInstance())
                            .commit()
                    true
                }
                R.id.menu_top250 -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.container, Top250Fragment.newInstance())
                            .commit()
                    true
                }
                R.id.menu_saved -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SavedMovieListFragment.newInstance())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_menu_navigation, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}