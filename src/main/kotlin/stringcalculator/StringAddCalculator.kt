package stringcalculator

object StringAddCalculator {
    fun add(expression: String?): Int {
        if (expression.isNullOrEmpty()) return 0

        return StringParser.parse(expression).sum()
    }
}
