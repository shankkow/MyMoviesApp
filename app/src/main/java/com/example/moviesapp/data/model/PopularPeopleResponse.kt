package com.example.moviesapp.data.model

data class PopularPeopleResponse(
    val page: Int,
    val results: List<Person>,
    val total_pages: Int,
    val total_results: Int
)

data class Person(
    val id: Int,
    val name: String,
    val profile_path: String?
)