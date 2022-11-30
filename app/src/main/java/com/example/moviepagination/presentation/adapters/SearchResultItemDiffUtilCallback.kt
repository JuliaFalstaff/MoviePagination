package com.example.moviepagination.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.moviepagination.domain.entities.search.Result

object SearchResultItemDiffUtilCallback :
    DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(
        oldItem: Result,
        newItem: Result
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Result,
        newItem: Result
    ): Boolean {
        return oldItem == newItem
    }
}