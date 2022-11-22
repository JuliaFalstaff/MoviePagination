package com.example.moviepagination.presentation.adapters

import com.example.moviepagination.domain.entities.Item

interface IOnListItemClickListener {
    fun onItemClick(movie: Item)
}