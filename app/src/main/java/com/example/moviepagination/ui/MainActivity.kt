package com.example.moviepagination.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviepagination.R
import com.example.moviepagination.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MovieListFragment.newInstance())
                    .commit()
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}