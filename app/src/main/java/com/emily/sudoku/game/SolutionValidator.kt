package com.emily.sudoku.game

object SolutionValidator {

    fun validateSolution(board: Array<IntArray>): Boolean {
        for (row in board) {
            if (!isValidGroup(row)) return false
        }

        for (col in 0 until 9) {
            val column = IntArray(9) { row -> board[row][col] }
            if (!isValidGroup(column)) return false
        }

        return checkSubGrids(board)
    }

    private fun checkSubGrids(board: Array<IntArray>): Boolean {
        for (i in 0 until 9 step 3) {
            for (j in 0 until 9 step 3) {
                val subBox = IntArray(9) { k ->
                    board[i + k / 3][j + k % 3]
                }
                if (!isValidGroup(subBox)) return false
            }
        }
        return true
    }

    private fun isValidGroup(group: IntArray): Boolean {
        val seen = BooleanArray(10)
        for (num in group) {
            if (num != 0) {
                if (seen[num]) return false
                seen[num] = true
            }
        }
        return true
    }
}