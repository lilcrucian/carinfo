package com.example.carinfo.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.carinfo.data.models.User
import com.example.carinfo.data.repository.AppDatabase

class LoginViewModel(private val database: AppDatabase) : ViewModel() {
    init {
        println("qwe")
    }
    var username by   mutableStateOf("")
    var password by  mutableStateOf("")
    var errorMessage by   mutableStateOf("")
    fun login(username: String, password: String): Boolean
    {
        val userDao = database.userDao()
        val user: User? = userDao.login(username,)
        return user?.password == password
    }
}