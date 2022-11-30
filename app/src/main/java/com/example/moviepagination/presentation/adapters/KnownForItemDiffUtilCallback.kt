package com.example.moviepagination.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.moviepagination.domain.entities.castInfo.KnownFor

object KnownForItemDiffUtilCallback : DiffUtil.ItemCallback<KnownFor>() {
    override fun areItemsTheSame(oldItem: KnownFor, newItem: KnownFor): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: KnownFor, newItem: KnownFor): Boolean {
        return oldItem == newItem
    }
}