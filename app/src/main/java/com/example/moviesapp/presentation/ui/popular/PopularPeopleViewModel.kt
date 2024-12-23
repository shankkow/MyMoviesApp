package com.example.moviesapp.presentation.ui.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.usecase.GetPopularPeopleUseCase
import com.example.moviesapp.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularPeopleViewModel @Inject constructor(
    private val getPopularPeopleUseCase: GetPopularPeopleUseCase
) : ViewModel() {

    private val _popularPeopleState = MutableStateFlow<Resource<List<com.example.moviesapp.domain.model.Person>>>(Resource.Loading())
    val popularPeopleState: StateFlow<Resource<List<com.example.moviesapp.domain.model.Person>>> = _popularPeopleState

    init {
        fetchPopularPeople()
    }

    fun fetchPopularPeople(page: Int = 1) {
        viewModelScope.launch {
            _popularPeopleState.value = getPopularPeopleUseCase(page)
        }
    }
}
