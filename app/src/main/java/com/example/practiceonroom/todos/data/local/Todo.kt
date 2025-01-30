package com.example.practiceonroom.todos.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("todo_table")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("todo_id")
    val id: Int = 0,
    @ColumnInfo("todo_title")
    var title: String,
    @ColumnInfo("todo_body")
    var body: String,

    )
