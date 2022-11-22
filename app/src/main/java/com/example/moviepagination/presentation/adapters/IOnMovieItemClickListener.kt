package com.example.moviepagination.presentation.adapters

import com.example.moviepagination.domain.entities.castInfo.CastMovie
import com.example.moviepagination.domain.entities.castInfo.KnownFor

interface IOnMovieItemClickListener {
    fun onItemClick(movie: CastMovie)
    fun onItemKnownClick(movie: KnownFor)
}