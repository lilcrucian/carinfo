package com.example.carinfo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.carinfo.data.repository.AppDatabase

class LoginViewModelFactory(
    private val database: AppDatabase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}