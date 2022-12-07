package com.example.moviepagination.presentation.core

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.moviepagination.R

object GlideFactory: ILoadImage  {
    override fun loadPicture(view: View, url: String?, imageView: ImageView) {
        Glide.with(view)
            .load(url)
            .placeholder(R.drawable.progress_animation)
            .error(R.drawable.ic_load_error_vector)
            .into(imageView)
    }
}