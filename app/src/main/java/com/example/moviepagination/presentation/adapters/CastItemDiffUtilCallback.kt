package com.example.moviepagination.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.moviepagination.domain.entities.castInfo.CastMovie

object CastItemDiffUtilCallback : DiffUtil.ItemCallback<CastMovie>() {
    override fun areItemsTheSame(oldItem: CastMovie, newItem: CastMovie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CastMovie, newItem: CastMovie): Boolean {
        return oldItem == newItem
    }
}