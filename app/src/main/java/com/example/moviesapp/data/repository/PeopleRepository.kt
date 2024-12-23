package com.example.moviesapp.data.repository

import com.example.moviesapp.data.api.ApiService
import com.example.moviesapp.data.model.PopularPeopleResponse
import com.example.moviesapp.data.model.PersonDetails
import com.example.moviesapp.data.model.PersonImagesResponse
import javax.inject.Inject

class PeopleRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getPopularPeople(apiKey: String, page: Int): PopularPeopleResponse {
        return apiService.getPopularPeople(apiKey, page) // Coroutine call
    }

    suspend fun searchPeople(apiKey: String, query: String, page: Int): PopularPeopleResponse {
        return apiService.searchPeople(apiKey, query, page) // Coroutine call
    }

    suspend fun getPersonDetails(apiKey: String, personId: Int): PersonDetails {
        return apiService.getPersonDetails(personId, apiKey) // Coroutine call
    }

    suspend fun getPersonImages(apiKey: String, personId: Int): PersonImagesResponse {
        return apiService.getPersonImages(personId, apiKey) // Coroutine call
    }
}
