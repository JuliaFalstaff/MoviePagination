package com.example.moviepagination.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviepagination.R
import com.example.moviepagination.databinding.ItemSearchResultRecyclerViewBinding
import com.example.moviepagination.domain.entities.search.Result


class SearchResultListAdapter(
    var searchResult: List<Result>,
    private var onListItemListener: IOnSearchItemClickListener
) : RecyclerView.Adapter<SearchResultListAdapter.ResultsViewHolder>() {

    fun setResultsData(list: List<Result>) {
        searchResult = list
        notifyDataSetChanged()
    }

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
        holder.bind(searchResult[position])
    }

    override fun getItemCount(): Int = searchResult.size

    inner class ResultsViewHolder(val binding: ItemSearchResultRecyclerViewBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(searchResult: Result) = with(binding) {
            resultTitleTextView.text = searchResult.title
            resultDescriptionTextView.text = searchResult.description
            Glide.with(itemView)
                    .load(searchResult.image)
                    .error(R.drawable.ic_load_error_vector)
                    .into(resultPosterImageView)
            itemView.setOnClickListener {
                onListItemListener.onItemSearchClick(searchResult)
            }
        }
    }
}