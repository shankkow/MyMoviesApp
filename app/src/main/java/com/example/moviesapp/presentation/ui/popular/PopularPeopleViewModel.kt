package com.example.moviesapp.presentation.ui.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.repository.PeopleRepository
import com.example.moviesapp.utils.Constants
import com.example.moviesapp.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PopularPeopleViewModel(private val repository: PeopleRepository) : ViewModel() {

    private val _popularPeopleState = MutableStateFlow<Resource<List<com.example.moviesapp.data.model.Person>>>(Resource.Loading())
    val popularPeopleState: StateFlow<Resource<List<com.example.moviesapp.data.model.Person>>> = _popularPeopleState

    init {
        fetchPopularPeople()
    }

    private fun fetchPopularPeople(page: Int = 1) {
        viewModelScope.launch {
            try {
                val response = repository.getPopularPeople(Constants.API_KEY, page)
                _popularPeopleState.value = Resource.Success(response.results)
            } catch (e: Exception) {
                _popularPeopleState.value = Resource.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}