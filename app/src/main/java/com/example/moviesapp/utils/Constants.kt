package com.example.moviesapp.utils

import com.example.moviesapp.BuildConfig

object Constants {
    const val API_KEY = BuildConfig.TMDB_API_KEY // Securely fetched from BuildConfig
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
}
