package com.example.moviesapp.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.repository.PeopleRepository
import com.example.moviesapp.utils.Constants
import com.example.moviesapp.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchPeopleViewModel(private val repository: PeopleRepository) : ViewModel() {

    private val _searchResultsState = MutableStateFlow<Resource<List<com.example.moviesapp.data.model.Person>>>(Resource.Loading())
    val searchResultsState: StateFlow<Resource<List<com.example.moviesapp.data.model.Person>>> = _searchResultsState

    fun searchPeople(query: String, page: Int = 1) {
        viewModelScope.launch {
            try {
                val response = repository.searchPeople(Constants.API_KEY, query, page)
                _searchResultsState.value = Resource.Success(response.results)
            } catch (e: Exception) {
                _searchResultsState.value = Resource.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}
