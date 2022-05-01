package com.example.moviepagination.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepagination.R
import com.example.moviepagination.databinding.ItemMovieRecyclerViewBinding
import com.example.moviepagination.model.data.Item

class MovieListAdapter(
    var movieList: List<Item>,
    private var onListItemListener: IOnListItemClickListener
) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    fun setData(list: List<Item>) {
        movieList = list
        notifyDataSetChanged()
    }

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
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    inner class MovieViewHolder(val binding: ItemMovieRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Item) = with(binding) {
            titleMovieTextView.text = movie.title
            dateOfReleaseTextView.text = movie.year
            movieRatingBar.rating = movie.imDbRating.toFloat().div(2)
            Glide.with(itemView)
                .load(movie.image)
                .error(R.drawable.ic_load_error_vector)
                .into(moviePosterImageView)
            itemView.setOnClickListener {
                onListItemListener.onItemClick(movie)
            }
        }
    }
}