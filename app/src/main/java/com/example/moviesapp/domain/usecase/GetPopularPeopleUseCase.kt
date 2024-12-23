package com.example.moviesapp.domain.usecase

import com.example.moviesapp.data.repository.PeopleRepository
import com.example.moviesapp.domain.model.Person
import com.example.moviesapp.utils.Constants
import com.example.moviesapp.utils.Resource
import javax.inject.Inject

class GetPopularPeopleUseCase @Inject constructor(
    private val repository: PeopleRepository
) {
    suspend operator fun invoke(page: Int): Resource<List<Person>> {
        return try {
            val response = repository.getPopularPeople(Constants.API_KEY, page)
            val people = response.results.map { person ->
                Person(
                    id = person.id,
                    name = person.name,
                    profilePath = person.profile_path
                )
            }
            Resource.Success(people)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred while fetching popular people.")
        }
    }
}
