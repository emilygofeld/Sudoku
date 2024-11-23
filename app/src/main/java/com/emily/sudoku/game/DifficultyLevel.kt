package com.emily.sudoku.game

enum class DifficultyLevel(val cellsToRemove: Int) {
    EASY(20),
    MEDIUM(40),
    HARD(60)
}
