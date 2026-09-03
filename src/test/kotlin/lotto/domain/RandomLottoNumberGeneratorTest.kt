package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RandomLottoNumberGeneratorTest {
    @Test
    fun `서로 다른 로또 번호 6개를 오름차순으로 생성한다`() {
        val numbers = RandomLottoNumberGenerator.generate()

        assertThat(numbers).hasSize(6)
        assertThat(numbers).doesNotHaveDuplicates()
        assertThat(numbers).allMatch { it in 1..45 }
        assertThat(numbers).isSorted()
    }
}
