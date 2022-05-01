package com.example.moviepagination.ui.adapters

import com.example.moviepagination.model.data.Item

interface IOnListItemClickListener {
    fun onItemClick(movie: Item)
}