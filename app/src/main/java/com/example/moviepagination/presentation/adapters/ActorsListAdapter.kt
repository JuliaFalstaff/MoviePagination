package com.example.moviepagination.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepagination.R
import com.example.moviepagination.databinding.ItemActorsRecyclerViewBinding
import com.example.moviepagination.domain.entities.info.Actor

class ActorsListAdapter(
    var actorsList: List<Actor>,
    private var onListItemListener: IOnActorClickListener
) : RecyclerView.Adapter<ActorsListAdapter.ActorsViewHolder>()  {

    fun setActorsData(list: List<Actor>) {
        actorsList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        return ActorsViewHolder(
            ItemActorsRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.bind(actorsList[position])
    }

    override fun getItemCount(): Int = actorsList.size

    inner class ActorsViewHolder(val binding: ItemActorsRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(actor: Actor) = with(binding) {
            binding.actorNameTexView.text = actor.name
            Glide.with(itemView)
                .load(actor.image)
                .error(R.drawable.ic_load_error_vector)
                .into(actorFotoImageView)
            itemView.setOnClickListener {
                onListItemListener.onActorItemClick(actor)
            }
        }
    }
}