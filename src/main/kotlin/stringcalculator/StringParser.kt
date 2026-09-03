package stringcalculator

internal object StringParser {
    private val customDelimiterPattern = Regex("^//(.)\n(.*)$")
    private val defaultDelimiters = charArrayOf(',', ':')

    fun parse(expression: String): List<Int> {
        val parsedExpression = parseExpression(expression)
        return parsedExpression.numbers
            .split(*parsedExpression.delimiters)
            .map(::toNonNegativeInt)
    }

    private fun parseExpression(expression: String): ParsedExpression {
        val result = customDelimiterPattern.matchEntire(expression)
        if (result == null) return ParsedExpression(expression, defaultDelimiters)

        val delimiter = result.groupValues[1].single()
        return ParsedExpression(result.groupValues[2], charArrayOf(delimiter))
    }

    private fun toNonNegativeInt(value: String): Int {
        val number = value.toIntOrNull() ?: throw IllegalArgumentException("숫자로 변환할 수 없습니다: $value")
        require(number >= 0) { "음수는 사용할 수 없습니다: $number" }
        return number
    }

    private data class ParsedExpression(
        val numbers: String,
        val delimiters: CharArray,
    )
}
