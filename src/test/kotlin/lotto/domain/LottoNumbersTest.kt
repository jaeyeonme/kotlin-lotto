package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class LottoNumbersTest {
    @Test
    fun `포장된 로또 번호 6개를 오름차순으로 관리한다`() {
        val lottoNumbers = LottoNumbers.from(listOf(45, 3, 21, 7, 1, 12).map(::LottoNumber))

        assertThat(lottoNumbers.values.map(LottoNumber::value)).containsExactly(1, 3, 7, 12, 21, 45)
    }

    @Test
    fun `포장된 로또 번호가 6개가 아니면 생성할 수 없다`() {
        assertThatThrownBy { LottoNumbers.from(listOf(1, 2, 3, 4, 5).map(::LottoNumber)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `포장된 로또 번호가 중복되면 생성할 수 없다`() {
        assertThatThrownBy { LottoNumbers.from(listOf(1, 2, 3, 4, 5, 5).map(::LottoNumber)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}
