package com.example.moviepagination.presentation.core


import android.content.Context
import android.view.View
import android.widget.ImageView

interface ILoadImage {
    fun loadPicture(view: View, url: String?, imageView: ImageView)
}