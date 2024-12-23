package com.example.moviesapp.domain.model

data class Person(
    val id: Int,
    val name: String,
    val profilePath: String?,
    val birthday: String? = null
)
