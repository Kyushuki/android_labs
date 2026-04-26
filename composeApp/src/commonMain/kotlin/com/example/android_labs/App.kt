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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.semantics.SemanticsActions.OnClick
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import jdk.jfr.Description


data class ShoppingListItem (
    val description: String,
    val bought: Boolean = false
)

@Composable
fun ShoppingListElement(item: ShoppingListItem, onBoughtChange: (Boolean) -> Unit, onDelete: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = item.bought,
            onCheckedChange = onBoughtChange
        )
        Text(item.description, Modifier.weight(1f))
        IconButton(onClick = onDelete) {
            Icon(Icons.Default.Delete, contentDescription = "Удалить")
        }
    }
}
@Composable
fun App() {
    Column {
        Card(
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
                        "Лабораторная №2",
                        style = MaterialTheme.typography.titleSmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Text(
                        "Динамическое изменение отображаемого интерфейса",
                        style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
        }
        val shoppingList = remember {
            mutableStateListOf(
                ShoppingListItem("Яйца 10"),
                ShoppingListItem("Молоко 1"),
                ShoppingListItem("Хлеб 3"),
                ShoppingListItem("Замороженная пицца 2"),
                ShoppingListItem("Йогурт 1"),
                ShoppingListItem("Лапша быстрого приготовления 5"),
                ShoppingListItem("Колбаса 2"),
                ShoppingListItem("Грудка 1"),
                ShoppingListItem("Пирожки 6"),
                ShoppingListItem("Шампуры 4"),
                ShoppingListItem("Гамак 2"),
                ShoppingListItem("Раскладное кресло 4"),
                ShoppingListItem("Палатка 2"),
                ShoppingListItem("Вода 5л. 4"),
                ShoppingListItem("Средство от комаров 4"),
                ShoppingListItem("Угли 3"),
                ShoppingListItem("Пластиковая посуда 10"),
                ShoppingListItem("Швейцарский нож")
            )
        }
        var newItemDesc by remember { mutableStateOf("")}
        LazyColumn(Modifier.fillMaxWidth()) {
            item {
                OutlinedTextField(value = newItemDesc, onValueChange = {newItemDesc = it},
                modifier = Modifier.padding(8.dp),
                label = {
                    Text("Название продукта")
                },
                trailingIcon = {
                    IconButton(onClick = {
                        if (newItemDesc.isNotBlank()) {
                            shoppingList.add(ShoppingListItem(newItemDesc.trim()))
                            newItemDesc = ""
                        }
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Добавить")
                    }
                })
            }
            itemsIndexed(shoppingList) { i, item ->
                ShoppingListElement(
                    item,
                    onBoughtChange = {
                        shoppingList[i] = item.copy(bought = it)
                    },
                    onDelete = {
                        shoppingList.removeAt(i)
                    }
                )

            }
        }
    }
}

