package com.example.carinfo.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.carinfo.data.repository.AppDatabase
import com.example.carinfo.ui.viewmodel.LoginViewModel
import com.example.carinfo.ui.viewmodel.LoginViewModelFactory

@Composable
fun LoginScreen(navController: NavHostController, context: Context) {
    val database = AppDatabase.getDatabase(context)
    val viewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(database))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = viewModel.username,
            onValueChange = { viewModel.username = it },
            label = { Text("Логин") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            label = { Text("Пароль") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            val loginSuccess = viewModel.login(viewModel.username,viewModel.password)
            if (loginSuccess) {
                navController.navigate("main")
            } else {
                viewModel.errorMessage = "Неверный логин или пароль"
            }
        }) {
            Text("Войти")
        }
        if (viewModel.errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(viewModel.errorMessage, color = androidx.compose.ui.graphics.Color.Red)
        }
    }
}
