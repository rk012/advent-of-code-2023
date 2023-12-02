fun main() {
    // Part 1

    fun part1(input: List<String>): Int {
        return input.size
    }


    // Part 2

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Testing
    val testInputPart1 = """
        lorem
        ipsum
        dolor
    """.trimIndent().lines()

    val testInputPart2 = """
        sit
        amet
    """.trimIndent().lines()

    check(part1(testInputPart1) == 3)
    check(part2(testInputPart2) == 2)

    val input = readInput("DayXX")
    part1(input).println()
    part2(input).println()
}
