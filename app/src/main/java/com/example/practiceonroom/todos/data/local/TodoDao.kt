package com.example.practiceonroom.todos.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {
    //    العمليات
    @Query("SELECT * FROM todo_table")
    fun getAllTodos(): LiveData<List<Todo>>

    //    insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTodoToTodos(todo: Todo)

    //    remove
    @Query("Delete FROM todo_table where todo_id=:id")
    fun deleteTodoFromTodos(id: Int)

    @Update
    fun updateTodo(todo: Todo)

}