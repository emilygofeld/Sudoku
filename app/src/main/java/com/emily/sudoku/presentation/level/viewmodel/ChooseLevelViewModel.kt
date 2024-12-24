package com.emily.sudoku.presentation.level.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emily.sudoku.core.Screen
import com.emily.sudoku.game.DifficultyLevel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ChooseLevelViewModel: ViewModel() {
    private val _selectedDifficulty = mutableStateOf(DifficultyLevel.MEDIUM)
    val selectedDifficulty = _selectedDifficulty

    private val _navigate = Channel<Screen>()
    val navigate = _navigate.receiveAsFlow()

    fun onEvent(event: ChooseLevelEvent) {
        when (event) {
            is ChooseLevelEvent.SelectLevel ->
                _selectedDifficulty.value = event.level
            ChooseLevelEvent.StartGame -> startGame()
        }
    }

    private fun startGame() {
        viewModelScope.launch {
            val difficulty = _selectedDifficulty.value
            _navigate.send(Screen.GameScreen(difficulty))
        }
    }
}