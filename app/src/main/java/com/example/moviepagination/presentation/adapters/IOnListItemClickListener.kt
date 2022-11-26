package com.example.moviepagination.presentation.adapters

import com.example.moviepagination.domain.entities.Item

interface IOnListItemClickListener<T> {
    fun onItemClick(movie: T)
}