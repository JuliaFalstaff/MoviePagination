package com.example.moviepagination.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepagination.R
import com.example.moviepagination.databinding.ItemActorKnownForRecyclerViewBinding
import com.example.moviepagination.domain.entities.castInfo.KnownFor

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