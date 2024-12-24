package com.emily.sudoku.core

import com.emily.sudoku.game.DifficultyLevel

sealed class Screen(val route: String) {
    data object DifficultyScreen: Screen("difficulty_screen")
    data class GameScreen(val difficulty: DifficultyLevel): Screen("game_screen/$difficulty") {
        companion object {
            const val ROUTE = "game_screen/{difficulty}"
        }
    }
}
