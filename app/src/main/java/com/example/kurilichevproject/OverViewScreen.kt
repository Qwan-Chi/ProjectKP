package com.example.kurilichevproject

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.kurilichevproject.db.Landmark
import com.example.kurilichevproject.db.LandmarkImage
import com.example.kurilichevproject.ui.theme.AppTheme
import org.jetbrains.exposed.sql.transactions.transaction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverView(navController: NavHostController) {
    //connectToDB()
    Scaffold(topBar = {
        // Верхняя панель навигации
        TopAppBar(
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(
                    "Обзор",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }, actions = {
                IconButton(onClick = { navController.navigate("Favorites") }) {
                    Icon(
                        imageVector = Icons.TwoTone.Star,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(36.dp),
                        contentDescription = "Localized description"
                    )
                }
            })
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {}
    }
    // Карточки
    val landmarks = remember { transaction { Landmark.all().toList() } }
    LazyColumn(
        Modifier
            .padding(top = 70.dp, start = 10.dp, end = 10.dp)
    ) {
        //connectToDB()
        items(landmarks.size) { landmarkId ->
            val landmark = landmarks[landmarkId]
            val image = remember {
                transaction {
                    landmark.images.first().image
                }
            }
            OutlinedCard(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    Modifier.clickable { navController.navigate("InfoView/${landmarkId + 1}") },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LaunchedEffect(null){
                        println(image)
                    }
                    SubcomposeAsyncImage(
                        model = image,
                        loading = {
                            CircularProgressIndicator()
                        },
                        contentDescription = "Изображение-превью карточки",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clip(RoundedCornerShape(10.dp))
                            .size(100.dp)
                            .padding(10.dp)
                            //.background(MaterialTheme.colorScheme.primaryContainer)
                    )
                    Column(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = landmark.title,
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Text(
                            text = landmark.address,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, wallpaper = Wallpapers.NONE)
@Composable
fun OverViewPreview() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            OverView(
                rememberNavController()
            )
        }
    }
}