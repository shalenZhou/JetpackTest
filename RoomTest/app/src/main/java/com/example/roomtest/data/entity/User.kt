package com.example.roomtest.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(var firstName: String, var lastName: String, var age: Int) {

    @PrimaryKey /* 主键 */ (autoGenerate = true) /* 主键的值是自动生成的 */
    var id: Long = 0
}