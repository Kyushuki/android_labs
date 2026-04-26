package com.example.android_labs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.painterResource

import androidlabs.composeapp.generated.resources.Res
import androidlabs.composeapp.generated.resources.compose_multiplatform
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import jdk.jfr.Description

@Composable
fun ShoppingListElement(description: String){
    Text(description, modifier = Modifier.padding(6.dp))
}
@Composable
fun App() {
    Column {
        Card (
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Image(
                    painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )
                Column {
                    Text(
                        "Лабораторная №1",
                        style = MaterialTheme.typography.titleSmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Text(
                        "Основы разметки Compose",
                        style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
        }
        val shoppingList = remember { listOf(
            "Яйца 10",
            "Молоко 1",
            "Хлеб 3",
            "Замороженная пицца 2",
            "Йогурт 1",
            "Лапша быстрого приготовления 5",
            "Колбаса 2",
            "Грудка 1",
            "Пирожки 6",
            "Шампуры 4",
            "Гамак 2",
            "Раскладное кресло 4",
            "Палатка 2",
            "Вода 5л. 4",
            "Средство от комаров 4",
            "Угли 3",
            "Пластиковая посуда 10",
            "Швейцарский нож"
        ) }
        LazyColumn (Modifier.fillMaxWidth()){
            items(shoppingList) {
                ShoppingListElement(it)
            }
        }
    }

}

