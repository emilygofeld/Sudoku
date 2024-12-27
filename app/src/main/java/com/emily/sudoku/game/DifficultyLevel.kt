package com.emily.sudoku.game

enum class DifficultyLevel(val cellsToRemove: Int) {
    EASY(20),
    MEDIUM(35),
    HARD(50)
}
