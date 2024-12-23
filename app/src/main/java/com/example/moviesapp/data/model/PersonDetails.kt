package com.example.moviesapp.data.model

data class PersonDetails(
    val id: Int,
    val name: String,
    val biography: String,
    val birthday: String?,
    val deathday: String?,
    val profile_path: String?
)