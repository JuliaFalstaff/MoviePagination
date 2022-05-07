package com.example.moviepagination.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepagination.R
import com.example.moviepagination.databinding.ItemActorCastMovieRecyclerViewBinding
import com.example.moviepagination.databinding.ItemActorsRecyclerViewBinding
import com.example.moviepagination.model.data.castInfo.CastMovie
import com.example.moviepagination.model.data.info.Actor

class CastMoviesListAdapter(
    var moviesList: List<CastMovie>,
    private var onListItemListener: IOnMovieItemClickListener
) : RecyclerView.Adapter<CastMoviesListAdapter.CastMoviesViewHolder>()  {

    fun setMovieData(list: List<CastMovie>) {
        moviesList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastMoviesViewHolder {
        return CastMoviesViewHolder(
                ItemActorCastMovieRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CastMoviesViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    override fun getItemCount(): Int = moviesList.size

    inner class CastMoviesViewHolder(val binding: ItemActorCastMovieRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: CastMovie) = with(binding) {
            castMovieTitleTextView.text = movie.title
            castMovieRoleTextView.text = movie.role
            castMovieYearTextView.text = movie.year
//            Glide.with(itemView)
//                .load(movie.)
//                .error(R.drawable.ic_load_error_vector)
//                .into(castMovieImageView)
            itemView.setOnClickListener {
                onListItemListener.onItemClick(movie)
            }
        }
    }

}