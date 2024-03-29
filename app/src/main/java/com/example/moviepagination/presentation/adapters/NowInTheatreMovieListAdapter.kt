package com.example.moviepagination.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepagination.databinding.ItemNowInTheatreRecyclerViewBinding
import com.example.moviepagination.domain.entities.Item
import com.example.moviepagination.presentation.core.BaseItemCallback
import com.example.moviepagination.presentation.glide.GlideFactory

class NowInTheatreMovieListAdapter(private var onListItemListener: IOnListItemClickListener<Item>) :
    ListAdapter<Item, NowInTheatreMovieListAdapter.MovieViewHolder>(BaseItemCallback<Item>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemNowInTheatreRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieViewHolder(val binding: ItemNowInTheatreRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Item) = with(binding) {
            movieTitleTextView.text = movie.title
            movieGenreTextView.text = movie.genres
            GlideFactory.loadPicture(itemView, movie.image, moviePosterImageView)
            itemView.setOnClickListener {
                onListItemListener.onItemClick(movie)
            }
        }
    }
}