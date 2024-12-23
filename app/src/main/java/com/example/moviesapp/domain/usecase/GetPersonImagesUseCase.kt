package com.example.moviesapp.domain.usecase

import com.example.moviesapp.data.repository.PeopleRepository
import com.example.moviesapp.utils.Constants
import com.example.moviesapp.utils.Resource
import javax.inject.Inject

class GetPersonImagesUseCase @Inject constructor(
    private val repository: PeopleRepository
) {
    suspend operator fun invoke(personId: Int): Resource<List<String>> {
        return try {
            val response = repository.getPersonImages(Constants.API_KEY, personId)
            val images = response.profiles.map { profile ->
                "https://image.tmdb.org/t/p/w500${profile.file_path}"
            }
            Resource.Success(images)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred while fetching person images.")
        }
    }
}
