package com.example.kurilichevproject

import android.icu.lang.UCharacter.VerticalOrientation
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import com.example.kurilichevproject.ui.theme.KurilichevProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverView() {
    Scaffold(topBar = {
        // Вверхний бар
        TopAppBar(
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(
                    "Обзор",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }, actions = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.Star,
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
    // Категории
    LazyRow(Modifier.padding(top = 65.dp, start = 10.dp, end = 10.dp)) {
        items(chips) { chip ->
            AssistChip(
                onClick = {},
                label = { Text(text = chip.text) },
                Modifier.padding(start = 10.dp, top = 5.dp)
            )
        }
    }
    //Карточки
    LazyColumn(
        Modifier
            .padding(top = 110.dp, start = 10.dp, end = 10.dp)
    ) {
        items(cards) { card ->
            OutlinedCard(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Row {
                    Image(
                        painter = painterResource(id = card.image),
                        contentDescription = "",
                        Modifier.size(120.dp).clip(
                            CircleShape)
                    )
                    Column(modifier = Modifier.fillMaxSize()){
                        Text(text = card.title, style = MaterialTheme.typography.headlineSmall)
                        Text(text = card.address, style = MaterialTheme.typography.labelMedium)
                    }
                }
            }
        }
    }
}

data class CardDTO(val image: Int, val title: String, val address: String)

val cards = listOf(
    CardDTO(R.drawable.star, title = "Тест название", address = "Адрес"),
    CardDTO(R.drawable.star, title = "Тест название", address = "Адрес")
)

data class ChipsDTO(val text: String)

val chips = listOf(
    ChipsDTO(text = "Тест"),
    ChipsDTO(text = "Тест2"),
    ChipsDTO(text = "Тест3")
)

@Preview(showBackground = true, wallpaper = Wallpapers.NONE)
@Composable
fun OverViewPreview() {
    KurilichevProjectTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            OverView()
        }
    }
}