package com.example.moviepagination.ui.adapters

import com.example.moviepagination.model.data.search.Result

interface IOnSearchItemClickListener {
    fun onItemSearchClick(movie: Result)
}