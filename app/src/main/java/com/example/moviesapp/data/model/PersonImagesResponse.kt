package com.example.moviesapp.data.model

data class PersonImagesResponse(
    val id: Int,
    val profiles: List<Image>
)

data class Image(
    val file_path: String
)