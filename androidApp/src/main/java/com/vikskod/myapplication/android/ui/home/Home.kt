package com.vikskod.myapplication.android.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.size.Scale.FILL
import coil.request.ImageRequest
import com.vikskod.myapplication.data.remote.dto.Location
import com.vikskod.myapplication.data.remote.dto.RestaurantX
import com.vikskod.myapplication.presentation.RestaurantListSideEffects
import com.vikskod.myapplication.presentation.RestaurantListViewModel
import org.koin.androidx.compose.get


@Composable
fun Home(viewModel: RestaurantListViewModel = get()) {

    LaunchedEffect(key1 = 1) {
        viewModel.onIntent(RestaurantListSideEffects.GetRestaurants)
    }

    val state by viewModel.state.collectAsState()
    Surface(modifier = Modifier.fillMaxSize()) {

        when {
            state.error.isError -> {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = state.error.errorMessage,
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            state.isSuccess -> {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        state.allRestaurantData?.restaurants?.let { restaurants ->
                            items(restaurants) { restaurant ->
                                RestaurantCard(restaurant.restaurant)
                            }
                        }
                    }
                )
            }

            state.isLoading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }

    }
}


@Composable
fun RestaurantCard(item: RestaurantX) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.large,
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .clip(MaterialTheme.shapes.large),
                model = ImageRequest.Builder(
                    LocalContext.current
                )
                    .scale(FILL)
                    .data(item.featuredImage)
                    .crossfade(true)
                    .build(),
                contentDescription = item.name,
                contentScale = ContentScale.FillBounds
            )

            Text(
                text = item.name,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 18.dp)
            )

            Text(
                text = item.location.address,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Light),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 14.dp)
            )
        }

    }

}

@Preview
@Composable
fun HomePreview() {
    RestaurantCard(
        item = RestaurantX(
            "http://google.com/photo",
            "1",
            Location(address = "Canberra ACT 2914"),
            "bbc"
        )
    )
}