package com.example.moviepagination.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.moviepagination.domain.entities.info.Actor

object ActorItemDiffUtilCallback: DiffUtil.ItemCallback<Actor>() {
    override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean {
        return oldItem == newItem
    }
}