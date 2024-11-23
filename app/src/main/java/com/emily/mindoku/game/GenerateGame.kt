package com.emily.mindoku.game


object GenerateGame {
    private fun generateFullBoard(): Array<IntArray> {
        val board = Array(9) {IntArray(9) {0}}
        fillBoard(board)
        return board
    }

    private fun fillBoard(board: Array<IntArray>): Boolean {
        for (row in 0..8) {
            for (col in 0..8) {
                if (board[row][col] == 0) {
                    val options = (1..9).shuffled()
                    for (option in options) {
                        if (isPlacementLegal(board, row, col, option)) {
                            board[row][col] = option
                            if (fillBoard(board)) {
                                return true
                            }
                            board[row][col] = 0
                        }
                    }
                }
                return false
            }
        }
        return true
    }


    private fun isPlacementLegal(board: Array<IntArray>, row: Int, col: Int, num: Int): Boolean {
        for (i in 0..8) {
            // check if another num exists in the same row/col
            if (board[row][i] == num || board[i][col] == num) return false
            // find subgrid and check if num already exists in that subgrid
            val boxRow = 3 * (row / 3) + i / 3
            val boxCol = 3 * (col / 3) + i % 3
            if (board[boxRow][boxCol] == num) return false
        }
        return true
    }

    private fun createGameBoard(difficultyLevel: DifficultyLevel): Array<IntArray> {
        val board = generateFullBoard()
        val cellsToRemove = difficultyLevel.cellsToRemove

        val gameBoard = board.map { it.clone() }.toTypedArray()
        var removed = 0

        while (removed < cellsToRemove) {
            val row = (0..8).random()
            val col = (0..8).random()
            if (gameBoard[row][col] != 0) {
                gameBoard[row][col] = 0
                removed++
            }
        }

        return gameBoard
    }

    // function solves board and then returns if more than one solution was found
    private fun solveBoard(board: Array<IntArray>, count: Int = 0): Int {
        var solutionCount = count
        for (row in 0..8) {
            for (col in 0..8) {
                if (board[row][col] == 0) {
                    for (num in 1..9) {
                        if (isPlacementLegal(board, row, col, num)) {
                            board[row][col] = num
                            solutionCount = solveBoard(board, solutionCount)
                            if (solutionCount > 1) return solutionCount
                            board[row][col] = 0
                        }
                    }
                    return solutionCount
                }
            }
        }
        return solutionCount + 1
    }

    fun validatedGameBoard(difficultyLevel: DifficultyLevel): Array<IntArray> {
        lateinit var board: Array<IntArray>
        do {
            board = createGameBoard(difficultyLevel)
        } while (solveBoard(board) != 1) // iterating until theres a board with a unique solution

        return board
    }
}


