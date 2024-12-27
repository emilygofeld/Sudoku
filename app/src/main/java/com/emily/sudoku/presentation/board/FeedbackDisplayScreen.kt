package com.emily.sudoku.presentation.board

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.emily.sudoku.presentation.board.viewmodel.GameState
import kotlinx.coroutines.delay

@Composable
fun GameStateDisplay(gameState: GameState) {
    var showError by remember { mutableStateOf(false) }

    LaunchedEffect(gameState) {
        if (gameState is GameState.Invalid) {
            showError = true
            delay(5000) // show error for 5 seconds
            showError = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = gameState is GameState.Completed || (gameState is GameState.Invalid && showError),
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Surface(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = when (gameState) {
                            is GameState.Completed -> MaterialTheme.colorScheme.primary
                            is GameState.Invalid -> MaterialTheme.colorScheme.error
                            else -> MaterialTheme.colorScheme.surface
                        },
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp),
                color = when (gameState) {
                    is GameState.Completed -> MaterialTheme.colorScheme.primaryContainer
                    is GameState.Invalid -> MaterialTheme.colorScheme.errorContainer
                    else -> MaterialTheme.colorScheme.surface
                },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = when (gameState) {
                        is GameState.Completed -> "Congratulations! You've completed the Sudoku puzzle!"
                        is GameState.Invalid -> "The current solution is invalid. Please check your answers."
                        else -> ""
                    },
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    color = when (gameState) {
                        is GameState.Completed -> MaterialTheme.colorScheme.onPrimaryContainer
                        is GameState.Invalid -> MaterialTheme.colorScheme.onErrorContainer
                        else -> MaterialTheme.colorScheme.onSurface
                    }
                )
            }
        }
    }
}

