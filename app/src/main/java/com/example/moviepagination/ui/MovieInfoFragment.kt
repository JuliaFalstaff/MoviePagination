package com.example.moviepagination.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.moviepagination.model.data.Item

class MovieInfoFragment: Fragment() {

    companion object {
        const val MOVIE_INFO = "Movie"
        fun newInstance(bundle: Bundle): MovieInfoFragment {
            val fragment = MovieInfoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}