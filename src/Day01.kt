fun main() {
    fun part1(input: List<String>): Int {

        val result = input.sumOf { line ->
            val lineNumberArray = line.filter { symbol -> symbol.isDigit() }
            "${lineNumberArray.first()}${lineNumberArray.last()}".toInt()
        }
        return result
    }

    val digitsMap = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )


    fun part2(input: List<String>): Int {

        return input.sumOf { line ->
            val numbersInWordsWithIndex =
                digitsMap.entries.map { it.value to line.allIndexesOf(it.key) }.filter { it.second.isNotEmpty() }
                    .flatMap { valueToIndexes -> valueToIndexes.second.map { valueToIndexes.first to it } }

            val numbersWithIndex = line.mapIndexed { index, char ->
                return@mapIndexed if (char.isDigit()) {
                    char.digitToInt() to index
                } else null
            }.filterNotNull()

            val allNumbersSortedByIndexesInLine = (numbersWithIndex + numbersInWordsWithIndex).sortedBy { it.second }
            allNumbersSortedByIndexesInLine.first().first * 10 + allNumbersSortedByIndexesInLine.last().first
        }
    }

    val testInput = readInput("Day01_test")
    check(part2(testInput) == 306)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

private fun String.allIndexesOf(substring: String): List<Int> {
    val indexes = mutableListOf<Int>()

    var lastIndex: Int = indexOf(substring)
    while (lastIndex != -1) {
        indexes.add(lastIndex)
        lastIndex = indexOf(substring, lastIndex + substring.length)
    }

    if (indexes.size > 2) {
        return listOf(indexes.first(), indexes.last())
    }

    return indexes
}
