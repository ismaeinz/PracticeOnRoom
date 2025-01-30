package com.example.practiceonroom.todos.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practiceonroom.MainApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel : ViewModel() {
    private val todoDao = TodoDataBase.getDaoInstance(MainApplication.getApplicationContext())
    val todoList: LiveData<List<Todo>> = todoDao.getAllTodos()


    fun addTodo(title: String, body: String) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.addTodoToTodos(
                Todo(
                    title = title, body = body
                )
            )
        }
    }

    fun removeTodo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.deleteTodoFromTodos(id)
        }
    }
//    Edit


    fun editTodo(title: String, body: String) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.updateTodo(
                Todo(
                    title = title, body = body
                )
            )
        }
    }
}