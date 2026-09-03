package lotto.domain

import org.assertj.core.api.Assertions.assertThat
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

    @Test
    fun `보너스 번호를 함께 관리한다`() {
        val winningNumbers = WinningNumbers.from(listOf(1, 2, 3, 4, 5, 6), bonusNumber = 7)

        assertThat(winningNumbers.matchesBonus(7)).isTrue()
        assertThat(winningNumbers.matchesBonus(8)).isFalse()
    }

    @Test
    fun `보너스 번호가 범위를 벗어나면 생성할 수 없다`() {
        assertThatThrownBy { WinningNumbers.from(listOf(1, 2, 3, 4, 5, 6), bonusNumber = 46) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `보너스 번호가 당첨 번호와 중복되면 생성할 수 없다`() {
        assertThatThrownBy { WinningNumbers.from(listOf(1, 2, 3, 4, 5, 6), bonusNumber = 6) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}
