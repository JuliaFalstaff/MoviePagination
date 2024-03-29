package com.example.moviepagination.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun isOnline(context: Context?): Boolean {
    val connectivityManager =
        context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    when (capabilities != null) {
        capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
        capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
        capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
    }
    return false
}