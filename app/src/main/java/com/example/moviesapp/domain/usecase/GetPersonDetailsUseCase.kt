package com.example.moviesapp.domain.usecase

import com.example.moviesapp.data.repository.PeopleRepository
import com.example.moviesapp.domain.model.Person
import com.example.moviesapp.utils.Constants
import com.example.moviesapp.utils.Resource
import javax.inject.Inject

class GetPersonDetailsUseCase @Inject constructor(
    private val repository: PeopleRepository
) {
    suspend operator fun invoke(personId: Int): Resource<Person> {
        return try {
            val response = repository.getPersonDetails(Constants.API_KEY, personId)
            val person = Person(
                id = response.id,
                name = response.name,
                profilePath = response.profile_path,
                birthday = response.birthday
            )
            Resource.Success(person)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred while fetching person details.")
        }
    }
}
