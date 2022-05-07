package com.example.moviepagination.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepagination.R
import com.example.moviepagination.databinding.ItemActorCastMovieRecyclerViewBinding
import com.example.moviepagination.databinding.ItemActorKnownForRecyclerViewBinding
import com.example.moviepagination.databinding.ItemActorsRecyclerViewBinding
import com.example.moviepagination.model.data.castInfo.CastMovie
import com.example.moviepagination.model.data.castInfo.KnownFor
import com.example.moviepagination.model.data.info.Actor

class KnownForMoviesListAdapter(
    var moviesList: List<KnownFor>,
    private var onListItemListener: IOnMovieItemClickListener
) : RecyclerView.Adapter<KnownForMoviesListAdapter.KnownForsViewHolder>()  {

    fun setKnownMovieData(list: List<KnownFor>) {
        moviesList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KnownForsViewHolder {
        return KnownForsViewHolder(
                ItemActorKnownForRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: KnownForsViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    override fun getItemCount(): Int = moviesList.size

    inner class KnownForsViewHolder(val binding: ItemActorKnownForRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: KnownFor) = with(binding) {
            knownForTitleTextView.text = movie.title
            knownForRoleTextView.text = movie.role
            knownForYearTextView.text = movie.year
            Glide.with(itemView)
                .load(movie.image)
                .error(R.drawable.ic_load_error_vector)
                .into(knownForImageView)
            itemView.setOnClickListener {
                onListItemListener.onItemKnownClick(movie)
            }
        }
    }

}