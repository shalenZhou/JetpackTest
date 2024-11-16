package com.example.roomtest.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roomtest.data.entity.User

@Dao
interface UserDao {

    @Insert // 将参数中传入的User对象插入数据库中，插入完成后还会将自动生成的主键id值返回。
    fun insertUser(user: User): Long

    @Update // 将参数中传入的User对象更新到数据库当中。
    fun updateUser(newUser: User)

    @Query("SELECT * FROM User")
    fun loadAllUsers(): List<User>

    @Query("SELECT * FROM User WHERE age > :age")
    fun loadUsersOlderThan(age: Int): List<User>

    @Delete // 将参数传入的User对象从数据库中删除。
    fun deleteUser(user: User)

    @Query("DELETE FROM User WHERE lastName = :lastName")
    // 如果是使用非实体类参数来增删改数据，那么也要编写SQL语句才行，而且这个时候不能使用@Insert、@Delete或@Update注解，而是都要使用@Query注解才行
    fun deleteUserByLastName(lastName: String): Int

}