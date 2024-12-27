package com.emily.sudoku.presentation.board.viewmodel

import androidx.lifecycle.ViewModel
import com.emily.sudoku.game.DifficultyLevel
import com.emily.sudoku.game.GameGenerator
import com.emily.sudoku.game.SolutionValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GameViewModel(
    level: DifficultyLevel
) : ViewModel() {

    private val initialBoard: Array<IntArray> = GameGenerator.generateUniqueSudokuBoard(level)

    private val _board = MutableStateFlow(initialBoard)
    val board: StateFlow<Array<IntArray>> = _board

    private val _selectedCell = MutableStateFlow(BoardPos(row = 0, col = 0))
    val selectedCell: StateFlow<BoardPos> = _selectedCell

    private val _gameState = MutableStateFlow<GameState>(GameState.InProgress)
    val gameState: StateFlow<GameState> = _gameState

    fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.OnChooseCell ->
                _selectedCell.value = selectedCell.value.copy(row = event.row, col = event.col)

            GameEvent.OnClearBoard -> {
                _board.value = initialBoard.map { it.clone() }.toTypedArray()
                _gameState.value = GameState.InProgress
            }

            GameEvent.OnClearCell -> {
                val (row, col) = _selectedCell.value
                _board.update { currentBoard ->
                    currentBoard.apply { this[row][col] = 0 }
                }
                _gameState.value = GameState.InProgress
            }

            is GameEvent.OnPlaceNumber -> {
                val (row, col) = _selectedCell.value
                _board.update { currentBoard ->
                    currentBoard.apply { this[row][col] = event.number }
                }
                checkBoardCompletion()
            }
        }
    }

    private fun checkBoardCompletion() {
        if (isBoardFull()) {
            val isValid = SolutionValidator.validateSolution(_board.value)
            _gameState.value = if (isValid) GameState.Completed else GameState.Invalid
        }
    }

    private fun isBoardFull(): Boolean {
        return _board.value.all { row -> row.all { it != 0 } }
    }
}

