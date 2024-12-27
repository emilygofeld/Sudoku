package com.emily.sudoku.presentation.board

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emily.sudoku.presentation.board.components.SudokuBoard
import com.emily.sudoku.presentation.board.viewmodel.GameEvent
import com.emily.sudoku.presentation.board.viewmodel.GameViewModel

@Composable
fun SudokuGameScreen(
    viewModel: GameViewModel,
) {
    val board by viewModel.board.collectAsState()
    val selectedCell by viewModel.selectedCell.collectAsState()
    val gameState by viewModel.gameState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sudoku!",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        SudokuBoard(
            board = board,
            selectedCell = selectedCell,
            onCellClick = { row, col ->
                viewModel.onEvent(GameEvent.OnChooseCell(row, col))
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        GameStateDisplay(gameState)

        Spacer(modifier = Modifier.height(16.dp))

        NumberPad(
            onNumberClick = { number ->
                viewModel.onEvent(GameEvent.OnPlaceNumber(number))
            }
        )

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { viewModel.onEvent(GameEvent.OnClearCell) },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("Clear Cell")
            }
            Button(
                onClick = { viewModel.onEvent(GameEvent.OnClearBoard) },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("Clear Board")
            }
        }
    }
}

@Composable
fun NumberPad(onNumberClick: (Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        content = {
            items(9) { number ->
                NumberButton(
                    number = number + 1,
                    onClick = {
                        onNumberClick(number + 1)
                    }
                )
            }
        },
        modifier = Modifier.height(160.dp)
    )
}

@Composable
fun NumberButton(number: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(1f),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
    ) {
        Text(
            text = number.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onTertiary
        )
    }
}

