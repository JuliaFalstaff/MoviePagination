package com.example.moviepagination.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepagination.databinding.ItemSavedMovieBinding
import com.example.moviepagination.domain.entities.info.MovieInfo
import com.example.moviepagination.presentation.core.BaseItemCallback
import com.example.moviepagination.presentation.glide.GlideFactory

class SavedMovieListAdapter(
    private var onListItemListener: IOnListItemClickListener<MovieInfo>
) : ListAdapter<MovieInfo, SavedMovieListAdapter.MovieViewHolder>(BaseItemCallback<MovieInfo>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemSavedMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieViewHolder(val binding: ItemSavedMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieInfo) = with(binding) {
            titleMovieTextView.text = movie.title
            movieRatingTextView.text = movie.imDbRating
            GlideFactory.loadPicture(itemView, movie.image, moviePosterImageView)
            itemView.setOnClickListener {
                onListItemListener.onItemClick(movie)
            }
        }
    }
}