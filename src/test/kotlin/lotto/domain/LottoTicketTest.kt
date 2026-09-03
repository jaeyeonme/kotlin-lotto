package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class LottoTicketTest {
    @Test
    fun `로또 번호 6개를 오름차순으로 관리한다`() {
        val ticket = LottoTicket.from(listOf(45, 3, 21, 7, 1, 12))

        assertThat(ticket.numbers).containsExactly(1, 3, 7, 12, 21, 45)
    }

    @Test
    fun `로또 번호가 6개가 아니면 생성할 수 없다`() {
        assertThatThrownBy { LottoTicket.from(listOf(1, 2, 3, 4, 5)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `로또 번호가 중복되면 생성할 수 없다`() {
        assertThatThrownBy { LottoTicket.from(listOf(1, 2, 3, 4, 5, 5)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `로또 번호가 범위를 벗어나면 생성할 수 없다`() {
        assertThatThrownBy { LottoTicket.from(listOf(0, 2, 3, 4, 5, 46)) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}
