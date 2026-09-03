package lotto.domain

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class WinningNumbersTest {
    @Test
    fun `당첨 번호는 서로 다른 번호 6개여야 한다`() {
        assertThatThrownBy { WinningNumbers.from(listOf(1, 2, 3, 4, 5, 5)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `당첨 번호는 1부터 45 사이여야 한다`() {
        assertThatThrownBy { WinningNumbers.from(listOf(1, 2, 3, 4, 5, 46)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}
