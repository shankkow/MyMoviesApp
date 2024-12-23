package com.example.moviesapp.presentation.ui.popular

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.moviesapp.data.model.Person
import com.example.moviesapp.data.utils.Resource

@Composable
fun PopularPeopleScreen() {
    // Assuming popularPeopleState will be fetched from ViewModel later.
    val popularPeopleState: Resource<List<Person>> = Resource.Loading() // Temporary loading state

    Scaffold(topBar = {
        TopAppBar(title = { Text("Popular People") })
    }) {
        when (val state = popularPeopleState) {
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
                        text = state.message ?: "Error loading data",
                        textAlign = TextAlign.Center
                    )
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
