package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class TicketCountTest {
    @Test
    fun `티켓 수를 값으로 관리한다`() {
        val ticketCount = TicketCount(3)

        assertThat(ticketCount.value).isEqualTo(3)
    }

    @Test
    fun `티켓 수가 음수이면 생성할 수 없다`() {
        assertThatThrownBy { TicketCount(-1) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}
