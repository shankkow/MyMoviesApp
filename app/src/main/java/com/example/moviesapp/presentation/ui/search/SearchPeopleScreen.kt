package com.example.moviesapp.presentation.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.moviesapp.data.model.Person
import com.example.moviesapp.utils.Resource
import com.example.moviesapp.utils.Resource

@Composable
fun SearchPeopleScreen(viewModel: SearchPeopleViewModel = viewModel()) {
    var query by remember { mutableStateOf("") }
    val searchResultsState by viewModel.searchResultsState.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = { Text("Search People") })
    }) {
        Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            TextField(
                value = query,
                onValueChange = {
                    query = it
                    if (query.isNotEmpty()) {
                        viewModel.searchPeople(query)
                    }
                },
                placeholder = { Text("Search for people...") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            when (val state = searchResultsState) {
                is Resource.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is Resource.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        items(state.data ?: emptyList()) { person ->
                            PersonItem(person)
                        }
                    }
                }
                is Resource.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = state.message ?: "Error loading search results",
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PersonItem(person: Person) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(
                data = "https://image.tmdb.org/t/p/w500${person.profile_path}"
            ),
            contentDescription = person.name,
            modifier = Modifier
                .size(64.dp)
                .padding(end = 8.dp)
        )
        Text(text = person.name, style = MaterialTheme.typography.body1)
    }
}
