package com.example.moviepagination.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepagination.R
import com.example.moviepagination.databinding.ItemActorKnownForRecyclerViewBinding
import com.example.moviepagination.domain.entities.castInfo.KnownFor
import com.example.moviepagination.presentation.core.BaseItemCallback
import com.example.moviepagination.presentation.core.GlideFactory

class KnownForMoviesListAdapter :
    ListAdapter<KnownFor, KnownForMoviesListAdapter.KnownForViewHolder>(
        BaseItemCallback<KnownFor>()
    ) {

    var onItemKnownForClickListener: ((KnownFor) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KnownForViewHolder {
        return KnownForViewHolder(
            ItemActorKnownForRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: KnownForViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class KnownForViewHolder(val binding: ItemActorKnownForRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: KnownFor) = with(binding) {
            knownForTitleTextView.text = movie.title
            knownForRoleTextView.text = movie.role
            knownForYearTextView.text = movie.year
            GlideFactory.loadPicture(itemView, movie.image, knownForImageView)
            itemView.setOnClickListener {
                onItemKnownForClickListener?.invoke(movie)
            }
        }
    }
}