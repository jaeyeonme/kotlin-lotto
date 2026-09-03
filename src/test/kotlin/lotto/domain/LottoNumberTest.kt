package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class LottoNumberTest {
    @Test
    fun `로또 번호를 값으로 관리한다`() {
        val lottoNumber = LottoNumber(1)

        assertThat(lottoNumber.value).isEqualTo(1)
    }

    @Test
    fun `로또 번호가 1보다 작으면 생성할 수 없다`() {
        assertThatThrownBy { LottoNumber(0) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `로또 번호가 45보다 크면 생성할 수 없다`() {
        assertThatThrownBy { LottoNumber(46) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}
