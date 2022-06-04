package lotto.domain

import lotto.domain.Lotto.Companion.LOTTO_NUMBER_COUNT
import lotto.domain.Lotto.Companion.LOTTO_NUMBER_RANGE
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LottoTest {
    @Test
    fun `RandomNumberGenerator를 생성자 파라미터로 받아 임의의 값으로 초기화한다`() {
        val lotto = Lotto(RandomNumberGenerator())
        Assertions.assertThat(LOTTO_NUMBER_RANGE.map { LottoNumber(it) }.count { number -> lotto.contains(number) })
            .isEqualTo(LOTTO_NUMBER_COUNT)
    }

    @Test
    fun `숫자목록 생성자 파라미터로 받아 임의의 값으로 초기화한다`() {
        val numbers = listOf(1, 5, 7, 11, 25, 45).map { LottoNumber(it) }
        val lotto = Lotto(numbers)

        LOTTO_NUMBER_RANGE.map { LottoNumber(it) }.forEach {
            Assertions.assertThat(lotto.contains(it)).isEqualTo(numbers.contains(it))
        }
    }

    @Test
    fun `로또 범위를 벗어나는 입력이 들어오면 예외를 반환한다`() {
        Assertions.assertThatThrownBy { Lotto(-1, 5, 7, 11, 25, 45) }
            .isInstanceOf(IllegalArgumentException::class.java)

        Assertions.assertThatThrownBy { Lotto(0, 5, 7, 11, 25, 46) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `중복된 숫자로 구성되면 예외를 반환한다`() {
        Assertions.assertThatThrownBy { Lotto(1, 1, 2, 3, 4, 5) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `Lotto 는 파라미터로 받은 Lotto와 비교해 서로 겹치는 숫자의 수를 반환한다`() {
        val sourceLotto = Lotto(1, 2, 3, 4, 5, 6)

        Assertions.assertThat(sourceLotto.countMatchedNumbers(sourceLotto)).isEqualTo(6)

        Assertions.assertThat(sourceLotto.countMatchedNumbers(Lotto(1, 3, 5, 7, 8, 9)))
            .isEqualTo(3)

        Assertions.assertThat(sourceLotto.countMatchedNumbers(Lotto(6, 7, 8, 9, 10, 11)))
            .isEqualTo(1)
    }

    @Test
    fun `Lotto에서 자동번호를 부여해 발급 가능하다`() {
        Assertions.assertThat(Lotto.getAutoLotto(0)).isEmpty()
        Assertions.assertThat(Lotto.getAutoLotto(1).size).isEqualTo(1)
        Assertions.assertThat(Lotto.getAutoLotto(100).size).isEqualTo(100)
    }
}