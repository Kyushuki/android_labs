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
import androidlabs.composeapp.generated.resources.addbtn
import androidlabs.composeapp.generated.resources.compose_multiplatform
import androidlabs.composeapp.generated.resources.example
import androidlabs.composeapp.generated.resources.lab_name
import androidlabs.composeapp.generated.resources.lab_num
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
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource



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
                        stringResource(Res.string.lab_num),
                        style = MaterialTheme.typography.titleSmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Text(
                        stringResource(Res.string.lab_name),
                        style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
        }
        val exampleText1 = stringResource(Res.string.example, 1)
        val exampleText2 = stringResource(Res.string.example, 2)
        val exampleText3 = stringResource(Res.string.example, 3)
        val shoppingList = remember {
            mutableStateListOf(
                ShoppingListItem(exampleText1),
                ShoppingListItem(exampleText2),
                ShoppingListItem(exampleText3)
            )
        }
        var newItemDesc by remember { mutableStateOf("")}
        LazyColumn(Modifier.fillMaxWidth()) {
            item {
                OutlinedTextField(value = newItemDesc, onValueChange = {newItemDesc = it},
                modifier = Modifier.padding(8.dp),
                label = {
                    Text(
                        stringResource(Res.string.addbtn)
                    )
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

