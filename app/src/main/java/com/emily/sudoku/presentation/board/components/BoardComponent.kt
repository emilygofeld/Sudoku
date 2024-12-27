package com.emily.sudoku.presentation.board.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.emily.sudoku.presentation.board.viewmodel.BoardPos

@Composable
fun SudokuBoard(
    board: Array<IntArray>,
    selectedCell: BoardPos,
    onCellClick: (Int, Int) -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .border(2.dp, MaterialTheme.colorScheme.primary)
    ) {
        // Draw Sudoku cells
        LazyVerticalGrid(
            columns = GridCells.Fixed(9),
            content = {
                items(81) { index ->
                    val row = index / 9
                    val col = index % 9
                    val isSelected = row == selectedCell.row && col == selectedCell.col
                    val isInSameRowOrCol = row == selectedCell.row || col == selectedCell.col
                    val isInSameBlock = (row / 3 == selectedCell.row / 3) && (col / 3 == selectedCell.col / 3)

                    SudokuCell(
                        number = board[row][col],
                        isSelected = isSelected,
                        isHighlighted = isInSameRowOrCol || isInSameBlock,
                        onClick = { onCellClick(row, col) }
                    )
                }
            }
        )

        // draw lines to separate 3x3 grids
        Canvas(modifier = Modifier.matchParentSize()) {
            val cellSize = size.width / 9 // size of each cell (assuming a square grid)
            val lineThickness = 2.dp.toPx()

            for (i in 1..2) {
                val x = i * 3 * cellSize
                drawLine(
                    color = Color.Black,
                    start = androidx.compose.ui.geometry.Offset(x, 0f),
                    end = androidx.compose.ui.geometry.Offset(x, size.height),
                    strokeWidth = lineThickness
                )
            }

            for (i in 1..2) {
                val y = i * 3 * cellSize
                drawLine(
                    color = Color.Black,
                    start = androidx.compose.ui.geometry.Offset(0f, y),
                    end = androidx.compose.ui.geometry.Offset(size.width, y),
                    strokeWidth = lineThickness
                )
            }
        }
    }
}
