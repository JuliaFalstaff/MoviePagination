package com.example.moviepagination.presentation.glide


import android.view.View
import android.widget.ImageView

interface ILoadImage {
    fun loadPicture(view: View, url: String?, imageView: ImageView)
}