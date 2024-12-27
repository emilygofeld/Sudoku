package com.emily.sudoku.core

import com.emily.sudoku.game.DifficultyLevel

sealed class Screen(val route: String) {
    data object ChooseLevelScreen: Screen("choose_level_screen")
    data class GameScreen(val level: DifficultyLevel): Screen("game_screen/$level") {
        companion object {
            const val ROUTE = "game_screen/{level}"
        }
    }
}
