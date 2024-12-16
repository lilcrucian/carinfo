package com.example.carinfo.data.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.carinfo.data.models.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE username = :username ")
    fun login(username: String,): User?

    @Insert
    suspend fun insertUser(user: User)
}