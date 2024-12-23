package com.example.moviesapp.presentation.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.usecase.GetPersonDetailsUseCase
import com.example.moviesapp.domain.usecase.GetPersonImagesUseCase
import com.example.moviesapp.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonDetailsViewModel @Inject constructor(
    private val getPersonDetailsUseCase: GetPersonDetailsUseCase,
    private val getPersonImagesUseCase: GetPersonImagesUseCase
) : ViewModel() {

    private val _personDetailsState = MutableStateFlow<Resource<com.example.moviesapp.domain.model.Person>>(Resource.Loading())
    val personDetailsState: StateFlow<Resource<com.example.moviesapp.domain.model.Person>> = _personDetailsState

    private val _personImagesState = MutableStateFlow<Resource<List<String>>>(Resource.Loading())
    val personImagesState: StateFlow<Resource<List<String>>> = _personImagesState

    fun fetchPersonDetails(personId: Int) {
        viewModelScope.launch {
            _personDetailsState.value = getPersonDetailsUseCase(personId)
        }
    }

    fun fetchPersonImages(personId: Int) {
        viewModelScope.launch {
            _personImagesState.value = getPersonImagesUseCase(personId)
        }
    }
}
