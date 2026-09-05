package stringcalculator

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.NullAndEmptySource

class StringAddCalculatorTest {
    @ParameterizedTest
    @NullAndEmptySource
    fun `빈 문자열 또는 null을 입력하면 0을 반환한다`(expression: String?) {
        assertThat(StringAddCalculator.add(expression)).isZero()
    }

    @Test
    fun `숫자 하나를 입력하면 해당 숫자를 반환한다`() {
        assertThat(StringAddCalculator.add("1")).isEqualTo(1)
    }

    @ParameterizedTest
    @CsvSource("1,2;3", "1:2;3", "1,2:3;6", delimiter = ';')
    fun `쉼표 또는 콜론으로 구분한 숫자를 합산한다`(
        expression: String,
        expected: Int,
    ) {
        assertThat(StringAddCalculator.add(expression)).isEqualTo(expected)
    }

    @Test
    fun `문자열 앞부분에 선언한 커스텀 구분자로 숫자를 합산한다`() {
        assertThat(StringAddCalculator.add("//;\n1;2;3")).isEqualTo(6)
    }

    @Test
    fun `음수가 포함되면 예외가 발생한다`() {
        assertThatThrownBy { StringAddCalculator.add("-1,2,3") }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `숫자가 아닌 값이 포함되면 예외가 발생한다`() {
        assertThatThrownBy { StringAddCalculator.add("1,a,3") }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}
