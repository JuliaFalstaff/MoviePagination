package com.example.moviepagination.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.example.moviepagination.R
import com.example.moviepagination.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigation()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MovieListFragment.newInstance())
                    .commit()
        }
    }

    private fun initBottomNavigation() {
        binding.bottomAppNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_bar_home -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.container, MovieListFragment.newInstance())
                            .commit()
                    true
                }
                R.id.bottom_bar_search -> {
                    TODO()
                }
                R.id.bottom_top250 -> {
                    TODO()
                }
                R.id.bottom_bar_fav -> {
                    TODO()
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_menu_navigation, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}