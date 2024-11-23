package com.emily.mindoku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.emily.mindoku.game.DifficultyLevel
import com.emily.mindoku.game.GenerateGame
import com.emily.mindoku.ui.theme.MindokuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MindokuTheme {
                val board = GenerateGame.validatedGameBoard(DifficultyLevel.EASY)
                for (row in 0..8) {
                    for (col in 0..8) {
                        print(board[row][col])
                    }
                    print("\n")
                }
            }
        }
    }
}

