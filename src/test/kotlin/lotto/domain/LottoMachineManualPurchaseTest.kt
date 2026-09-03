package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class LottoMachineManualPurchaseTest {
    private val machine = LottoMachine { listOf(1, 2, 3, 4, 5, 6) }

    @Test
    fun `수동 로또와 남은 수만큼 자동 로또를 함께 발급한다`() {
        val manualTickets =
            listOf(
                LottoTicket.from(listOf(7, 8, 9, 10, 11, 12)),
                LottoTicket.from(listOf(13, 14, 15, 16, 17, 18)),
                LottoTicket.from(listOf(19, 20, 21, 22, 23, 24)),
            )

        val purchase = machine.purchaseWithManualTickets(PurchaseAmount(14_000), manualTickets)

        assertThat(purchase.manualTicketCount.value).isEqualTo(3)
        assertThat(purchase.automaticTicketCount.value).isEqualTo(11)
        assertThat(purchase.tickets).hasSize(14)
        assertThat(purchase.tickets.take(3)).containsExactlyElementsOf(manualTickets)
    }

    @Test
    fun `수동 로또 수가 전체 구매 수를 넘으면 발급할 수 없다`() {
        val manualTickets = List(2) { LottoTicket.from(listOf(1, 2, 3, 4, 5, 6)) }

        assertThatThrownBy { machine.purchaseWithManualTickets(PurchaseAmount(1_000), manualTickets) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}
