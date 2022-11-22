package com.example.moviepagination.presentation.adapters

import com.example.moviepagination.domain.entities.info.Actor

interface IOnActorClickListener {
    fun onActorItemClick(actor: Actor)
}