package com.emily.sudoku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.emily.sudoku.core.Screen
import com.emily.sudoku.presentation.screens.DifficultyScreen
import com.emily.sudoku.presentation.viewmodel.DifficultyViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Screen.DifficultyScreen.route) {
                composable(Screen.DifficultyScreen.route) {
                    DifficultyScreen(vm = DifficultyViewModel(), navController = navController)
                    Box(
                        modifier = Modifier.fillMaxSize().background(color = Color.Blue)
                    )
                }
                composable(
                    route = Screen.GameScreen.ROUTE,
                    arguments = listOf(navArgument("difficulty") {type = NavType.StringType})
                ) {
//                    BoardScreen(navController = navController)
                }
            }
        }
    }
}


