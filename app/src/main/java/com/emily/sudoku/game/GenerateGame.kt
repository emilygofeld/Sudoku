package com.emily.sudoku.game

object GenerateGame {
    private fun generateFullBoard(): Array<IntArray> {
        val board = Array(9) {IntArray(9) {0}}
        fillBoard(board)
        return board
    }

    private fun fillBoard(board: Array<IntArray>): Boolean {
        return fillCell(board, 0, 0)
    }

    private fun fillCell(board: Array<IntArray>, row: Int, col: Int): Boolean {
        if (row == 9) return true // Reached the end of the board

        val nextRow = if (col == 8) row + 1 else row
        val nextCol = if (col == 8) 0 else col + 1

        if (board[row][col] != 0) {
            return fillCell(board, nextRow, nextCol)
        }

        val options = (1..9).shuffled()
        for (option in options) {
            if (isPlacementLegal(board, row, col, option)) {
                board[row][col] = option
                if (fillCell(board, nextRow, nextCol)) {
                    return true
                }
                board[row][col] = 0
            }
        }
        return false
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

    // Function to solve the board and return the count of solutions
    private fun solveBoard(board: Array<IntArray>, count: Int = 0): Int {
        return solve(board, 0, 0, count)
    }

    // Helper function to solve the board recursively
    private fun solve(board: Array<IntArray>, row: Int, col: Int, count: Int): Int {
        if (row == 9) return count + 1 // Solved entire board

        if (board[row][col] != 0) return solve(board, nextRow(row, col), nextCol(col), count)

        var solutionCount = count
        for (num in 1..9) {
            if (isPlacementLegal(board, row, col, num)) {
                board[row][col] = num
                solutionCount = solve(board, nextRow(row, col), nextCol(col), solutionCount)
                if (solutionCount > 1) return solutionCount // Early exit if more than one solution
                board[row][col] = 0
            }
        }
        return solutionCount
    }

    private fun nextRow(row: Int, col: Int): Int {
        return if (col == 8) row + 1 else row
    }

    private fun nextCol(col: Int): Int {
        return if (col == 8) 0 else col + 1
    }

    fun validatedGameBoard(difficultyLevel: DifficultyLevel): Array<IntArray> {
        lateinit var board: Array<IntArray>
        do {
            board = createGameBoard(difficultyLevel)
        } while (solveBoard(board) != 1) // Iterate until there's a board with a unique solution

        return board
    }

}


