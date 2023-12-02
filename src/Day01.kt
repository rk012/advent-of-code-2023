fun main() {
    // Part 1

    fun part1(input: List<String>): Int = input.sumOf { line ->
        val nums = line.filter { it.isDigit() }
        nums.first().digitToInt() * 10 + nums.last().digitToInt()
    }


    // Part 2

    fun parseNums(line: String): Sequence<Int> = sequence {
        val validNumbers = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

        var buf = ""

        for (ch in line) {
            if (ch.isDigit()) {
                buf = ""
                yield(ch.digitToInt())
                continue
            }

            buf += ch

            while (true) {
                val shouldAddChar: Boolean = when {
                    buf.isEmpty() -> true

                    buf in validNumbers -> {
                        yield(validNumbers.indexOf(buf) + 1)
                        buf = buf.drop(1)
                        false
                    }

                    validNumbers.any { it.startsWith(buf) } -> true

                    else -> {
                        buf = buf.drop(1)
                        false
                    }
                }

                if (shouldAddChar) break
            }
        }
    }

    fun part2(input: List<String>): Int = input.sumOf {
        val nums = parseNums(it)
        nums.first() * 10 + nums.last()
    }

    // Testing
    val testInputPart1 = """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
    """.trimIndent().lines()

    val testInputPart2 = """
        two1nine
        eightwothree
        abcone2threexyz
        xtwone3four
        4nineeightseven2
        zoneight234
        7pqrstsixteen
    """.trimIndent().lines()

    check(part1(testInputPart1) == 142)
    check(part2(testInputPart2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
