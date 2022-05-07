package com.example.moviepagination.ui.adapters

import com.example.moviepagination.model.data.Item
import com.example.moviepagination.model.data.castInfo.CastMovie
import com.example.moviepagination.model.data.castInfo.KnownFor

interface IOnMovieItemClickListener {
    fun onItemClick(movie: CastMovie)
    fun onItemKnownClick(movie: KnownFor)
}