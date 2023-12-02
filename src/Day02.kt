private data class GameDrawing(
    val red: Int,
    val green: Int,
    val blue: Int
) {
    fun isValid() = red <= 12 && green <= 13 && blue <= 14
}

fun main() {
    // Part 1
    fun maxGameDrawing(drawingsLine: String): GameDrawing {
        val drawings: List<Pair<Int, String>> = drawingsLine
            .split(',', ';')
            .map { it.trim().split(' ') }
            .map { it.first().toInt() to it.last() }

        var maxDrawing = GameDrawing(0, 0, 0)

        for ((num, col) in drawings) {
            maxDrawing = when (col.lowercase()) {
                "red" -> maxDrawing.copy(red = maxOf(maxDrawing.red, num))
                "green" -> maxDrawing.copy(green = maxOf(maxDrawing.green, num))
                "blue" -> maxDrawing.copy(blue = maxOf(maxDrawing.blue, num))
                else -> error("invalid color")
            }
        }

        return maxDrawing
    }

    fun part1(input: List<String>): Int = input
        .map {
            val (gameId, drawings) = it.split(':')

            gameId.drop(5).toInt() to maxGameDrawing(drawings)
        }
        .filter { it.second.isValid() }
        .sumOf { it.first }


    // Part 2

    fun part2(input: List<String>) = input
        .map { maxGameDrawing(it.split(':').last()) }
        .sumOf { it.red * it.green * it.blue }

    // Testing
    val testInputPart1 = """
        Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
        Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
        Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
        Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
    """.trimIndent().lines()

    val testInputPart2 = """
        Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
        Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
        Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
        Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
    """.trimIndent().lines()

    check(part1(testInputPart1) == 8)
    check(part2(testInputPart2) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
