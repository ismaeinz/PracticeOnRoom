package com.example.practiceonroom.todos.data.local

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.practiceonroom.R

@Composable
fun MainScreen(todoViewModel: TodoViewModel) {
    val todoList: List<Todo>? by todoViewModel.todoList.observeAsState()
    var title: String by rememberSaveable { mutableStateOf("") }
    var body: String by rememberSaveable { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 10.dp)

    ) {
        Text(
            text = "Simple Todo App On Room DataBase",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Card {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text(text = "Title ") },

                        )
                    OutlinedTextField(value = body,
                        onValueChange = { body = it },
                        label = { Text(text = "Subject ") }

                    )
                }
                IconButton(onClick = {
                    todoViewModel.addTodo(title, body)
                }, modifier = Modifier.weight(.1f)) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_add_24),
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .background(Color.Green)


                    )
                }
            }

        }
        todoList?.let {
            LazyColumn(content = {
                itemsIndexed(it) { index: Int, item: Todo ->
                    TodoItem(
                        item = item,
                        onDeleteTodo = { todoViewModel.removeTodo(item.id) },

                        )
                }
            })
        }
    }
}

@Composable
fun TodoItem(item: Todo, onDeleteTodo: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "title:${item.title}"
            )
            Text(
                text = "subject:${item.body}"
            )
        }
        IconButton(
            onClick = onDeleteTodo
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_delete_24),
                contentDescription = "",
                tint = Color.Black,

                )
        }
//        edit
        IconButton(
            onClick = {
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_edit_24),
                contentDescription = "",
                tint = Color.Black,

                )
        }
    }
}

