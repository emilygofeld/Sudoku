package com.emily.sudoku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.emily.sudoku.core.Screen
import com.emily.sudoku.presentation.board.BoardScreen
import com.emily.sudoku.presentation.level.ChooseLevelScreen
import com.emily.sudoku.presentation.level.viewmodel.ChooseLevelViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Screen.DifficultyScreen.route) {
                composable(Screen.DifficultyScreen.route) {
                    ChooseLevelScreen(vm = ChooseLevelViewModel(), navController = navController)
                }
                composable(
                    route = Screen.GameScreen.ROUTE,
                    arguments = listOf(navArgument("difficulty") {type = NavType.StringType})
                ) {
                    BoardScreen() //navController = navController
                }
            }
        }
    }
}


