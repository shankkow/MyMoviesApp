package com.example.moviesapp.data.api

import com.example.moviesapp.data.model.PersonDetails
import com.example.moviesapp.data.model.PersonImagesResponse
import com.example.moviesapp.data.model.PopularPeopleResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("person/popular")
    suspend fun getPopularPeople(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): PopularPeopleResponse

    @GET("search/person")
    suspend fun searchPeople(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): PopularPeopleResponse

    @GET("person/{person_id}")
    suspend fun getPersonDetails(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String
    ): PersonDetails

    @GET("person/{person_id}/images")
    suspend fun getPersonImages(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String
    ): PersonImagesResponse
}