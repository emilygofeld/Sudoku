package com.emily.sudoku.presentation.board.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SudokuCell(
    number: Int,
    isSelected: Boolean,
    isHighlighted: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .border(0.5.dp, MaterialTheme.colorScheme.outline)
            .background(
                when {
                    isSelected -> MaterialTheme.colorScheme.primaryContainer
                    isHighlighted -> MaterialTheme.colorScheme.secondaryContainer
                    else -> MaterialTheme.colorScheme.background
                }
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (number != 0) {
            Text(
                text = number.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
