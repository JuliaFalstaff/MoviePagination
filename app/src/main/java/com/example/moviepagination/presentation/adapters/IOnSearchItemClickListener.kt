package com.example.moviepagination.presentation.adapters

import com.example.moviepagination.domain.entities.search.Result

interface IOnSearchItemClickListener {
    fun onItemSearchClick(movie: Result)
}