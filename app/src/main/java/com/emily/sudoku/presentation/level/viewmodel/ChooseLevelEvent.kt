package com.emily.sudoku.presentation.level.viewmodel

import com.emily.sudoku.game.DifficultyLevel

sealed interface ChooseLevelEvent {
    data class SelectLevel(val level: DifficultyLevel): ChooseLevelEvent
    data object StartGame: ChooseLevelEvent
}