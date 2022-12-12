package com.example.moviepagination.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviepagination.databinding.ItemSearchResultRecyclerViewBinding
import com.example.moviepagination.domain.entities.search.Result
import com.example.moviepagination.presentation.core.BaseItemCallback
import com.example.moviepagination.presentation.glide.GlideFactory


class SearchResultListAdapter(
) : ListAdapter<Result, SearchResultListAdapter.ResultsViewHolder>(BaseItemCallback<Result>()) {

    var listener: ((Result) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        return ResultsViewHolder(
            ItemSearchResultRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ResultsViewHolder(val binding: ItemSearchResultRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(searchResult: Result) = with(binding) {
            resultTitleTextView.text = searchResult.title
            resultDescriptionTextView.text = searchResult.description
            GlideFactory.loadPicture(itemView, searchResult.image, resultPosterImageView)
            itemView.setOnClickListener {
                listener?.invoke(searchResult)
            }
        }
    }
}