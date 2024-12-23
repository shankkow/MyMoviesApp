package com.example.moviesapp.presentation.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.moviesapp.presentation.components.LoadingIndicator
import com.example.moviesapp.presentation.components.ErrorView
import com.example.moviesapp.utils.Resource

@Composable
fun PersonDetailsScreen(
    personId: Int,
    viewModel: PersonDetailsViewModel = viewModel()
) {
    val personDetailsState by viewModel.personDetailsState.collectAsState()
    val personImagesState by viewModel.personImagesState.collectAsState()

    // Fetch data when this screen is loaded
    LaunchedEffect(Unit) {
        viewModel.fetchPersonDetails(personId)
        viewModel.fetchPersonImages(personId)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Person Details") })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            when (val state = personDetailsState) {
                is Resource.Loading -> LoadingIndicator()
                is Resource.Success -> {
                    val person = state.data
                    person?.let {
                        PersonDetailsContent(person.name, person.profilePath, person.birthday)
                    }
                }
                is Resource.Error -> ErrorView(message = state.message ?: "Failed to load person details")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Images",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(8.dp)
            )

            when (val state = personImagesState) {
                is Resource.Loading -> LoadingIndicator()
                is Resource.Success -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        items(state.data ?: emptyList()) { imageUrl ->
                            PersonImageItem(imageUrl)
                        }
                    }
                }
                is Resource.Error -> ErrorView(message = state.message ?: "Failed to load images")
            }
        }
    }
}

@Composable
fun PersonDetailsContent(name: String, profilePath: String?, birthday: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(
                data = "https://image.tmdb.org/t/p/w500${profilePath}",
                builder = { crossfade(true) }
            ),
            contentDescription = name,
            modifier = Modifier
                .size(100.dp)
                .padding(end = 8.dp)
        )
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = name, style = MaterialTheme.typography.h6)
            Text(text = "Birthday: ${birthday ?: "N/A"}", style = MaterialTheme.typography.body2)
        }
    }
}

@Composable
fun PersonImageItem(imageUrl: String) {
    Image(
        painter = rememberImagePainter(data = imageUrl),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(4.dp)
    )
}
