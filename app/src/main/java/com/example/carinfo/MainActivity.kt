package com.example.carinfo
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carinfo.ui.screens.LoginScreen
import com.example.carinfo.ui.screens.MainScreen
import com.example.carinfo.ui.theme.CarInfoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarInfoTheme {
                println("bebra")
                val navController = rememberNavController()
                AppNavHost(navController = navController, context = this)
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController, context: Context) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = "login"

    ) {
        composable("login") {println("toxa"); LoginScreen(navController, context) }
        composable("main") { MainScreen(navController, context) }
    }
}