package com.emily.sudoku.presentation.board.viewmodel

sealed interface GameEvent {
    data class OnChooseCell(val row: Int, val col: Int): GameEvent
    data class OnPlaceNumber(val number: Int): GameEvent
    data object OnClearCell: GameEvent
    data object OnClearBoard: GameEvent
}