package com.example.moviepagination.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepagination.R
import com.example.moviepagination.databinding.ItemMovieRecyclerViewBinding
import com.example.moviepagination.domain.entities.Item
import com.example.moviepagination.presentation.core.BaseItemCallback
import com.example.moviepagination.presentation.core.GlideFactory

class Top250TvSeriesAdapter(
    private var onListItemListener: IOnListItemClickListener<Item>,
) : ListAdapter<Item, Top250TvSeriesAdapter.MovieViewHolder>(BaseItemCallback<Item>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieViewHolder(val binding: ItemMovieRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Item) = with(binding) {
            titleMovieTextView.text = movie.title
            dateOfReleaseTextView.text = movie.year
            movieRatingTextView.text = movie.imDbRating
            GlideFactory.loadPicture(itemView, movie.image, moviePosterImageView)
            itemView.setOnClickListener {
                onListItemListener.onItemClick(movie)
            }
        }
    }
}