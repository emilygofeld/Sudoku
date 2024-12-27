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
import com.emily.sudoku.game.DifficultyLevel
import com.emily.sudoku.presentation.board.SudokuGameScreen
import com.emily.sudoku.presentation.board.viewmodel.BoardViewModel
import com.emily.sudoku.presentation.level.ChooseLevelScreen
import com.emily.sudoku.presentation.level.viewmodel.ChooseLevelViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Screen.ChooseLevelScreen.route) {
                composable(Screen.ChooseLevelScreen.route) {
                    ChooseLevelScreen(vm = ChooseLevelViewModel(), navController = navController)
                }
                composable(
                    route = Screen.GameScreen.ROUTE,
                    arguments = listOf(navArgument("level") {type = NavType.StringType})
                ) {
                    val level = it.arguments?.getString("level")?.let { arg ->
                        try {
                            DifficultyLevel.valueOf(arg.uppercase())
                        } catch (e: IllegalArgumentException) {
                            throw IllegalArgumentException("Invalid difficulty level: $arg")
                        }
                    } ?: throw IllegalArgumentException("Difficulty level is required")
                    SudokuGameScreen(BoardViewModel(level))
                }
            }
        }
    }
}


