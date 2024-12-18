package xyz.epicebic.aoc.util


class WordSearch(val input: List<String>) {

    fun search(word: String): Int {
        return search(word, *Direction.entries.toTypedArray())
    }

    fun search(word: String, vararg directions: Direction): Int {
        val startChar = word.first()
        val endChar = word.last()
        var counter = 0

        for ((x, row) in input.withIndex()) {
            for ((z, column) in row.withIndex()) {
                if (column != startChar) continue

                for (direction in directions) {

                    var dirX = x + direction.x
                    var dirZ = z + direction.z
                    var lastChar = startChar

                    for (index in 1 until word.length) {
                        if (dirX < 0) break
                        if (dirX >= input.size) break
                        if (dirZ < 0) break
                        if (dirZ >= row.length) break

                        val currentChar = input[dirX][dirZ]
                        if (currentChar != nextChar(word, index)) {
                            break
                        }

                        lastChar = currentChar

                        dirX += direction.x
                        dirZ += direction.z
                    }

                    if (lastChar == endChar) counter++
                }
            }
        }
        return counter
    }

    private fun nextChar(word: String, index: Int): Char {
        if (index < 0 || index >= word.length) return ' '
        return word[index]
    }
}

