package com.emily.sudoku.presentation.board.viewmodel

sealed class GameState {
    data object InProgress : GameState()
    data object Completed : GameState()
    data object Invalid : GameState()
}