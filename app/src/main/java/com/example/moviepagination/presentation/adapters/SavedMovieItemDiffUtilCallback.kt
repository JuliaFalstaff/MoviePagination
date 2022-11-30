package com.example.moviepagination.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.moviepagination.domain.entities.info.MovieInfo

object SavedMovieItemDiffUtilCallback: DiffUtil.ItemCallback<MovieInfo>() {
    override fun areItemsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
        return oldItem == newItem
    }
}