package com.example.roomtest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roomtest.data.entity.Book

@Dao
interface BookDao {

    @Insert
    fun insertBook(book: Book): Long

    @Query("SELECT * FROM Book")
    fun loadAllBooks(): List<Book>

}