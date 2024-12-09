package xyz.epicebic.aoc.util

class Grid(val rows: Int, val cols: Int, var currentRow: Int, var currentCol: Int, private val grid: MutableList<MutableList<Char>>) {

    constructor(rows: Int, cols: Int, grid: MutableList<MutableList<Char>>): this(rows, cols, 0, 0, grid)

    val currentPos get() = currentRow to currentCol

    fun move(row: Int, col: Int): Grid {
        if (row < 0 || row >= rows || col < 0 || col >= cols) error("Invalid access of grid at $row, $col")

        currentRow = row
        currentCol = col

        return this
    }

    fun look(row: Int, col: Int): Char? {
        if (row < 0 || row >= rows || col < 0 || col >= cols) return null

        return grid[row][col]
    }

    fun move(direction: Direction): Grid {
        return move(currentRow + direction.x, currentCol + direction.z)
    }

    fun look(direction: Direction): Char? {
        return look(currentRow + direction.x, currentCol + direction.z)
    }

    fun locateFirst(character: Char): Pair<Int, Int> {
        for (row in 0..rows) {
            for (col in 0..cols) {
                if(look(row, col) == character) return row to col
            }
        }

        error("Unable to locate character '$character'")
    }

    fun moveToFirst(character: Char): Grid {
        val location = locateFirst(character)

        currentRow = location.first
        currentCol = location.second
        return this
    }

    fun set(character: Char): Grid {
        grid[currentRow][currentCol] = character

        return this
    }

    fun currentChar(): Char {
        return grid[currentRow][currentCol]
    }

    fun clone(): Grid {
        val copy = ArrayList<ArrayList<Char>>(rows)

        for (row in 0 until rows) {
            copy.add(ArrayList(cols))

            for (col in 0 until cols) {
                copy[row].add(grid[row][col])
            }
        }

        return Grid(rows, cols, currentRow, currentCol, copy.toMutableList())
    }

    override fun toString(): String {
        return "Grid(rows=$rows, cols=$cols, currentRow=$currentRow, currentCol=$currentCol, grid=\n${grid.joinToString("\n") }})"
    }


    companion object {
        fun create(input: List<String>): Grid {
            val grid = input.map { it.toCharArray().toMutableList() }
            if (!grid.map { it.size }.all { size -> grid[0].size == size }) {
                error("Grid has mis-matched column amounts")
            }

            return Grid(grid.size, grid[0].size, grid.toMutableList())
        }
    }

}