package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoResultTest {
    @Test
    fun `일치 개수별 당첨 티켓 수를 집계한다`() {
        val winningNumbers = WinningNumbers.from(listOf(1, 2, 3, 4, 5, 6))
        val tickets = winningTickets()

        val result = LottoResult.from(tickets, winningNumbers)

        assertThat(result.count(LottoRank.THREE_MATCHES)).isEqualTo(1)
        assertThat(result.count(LottoRank.FOUR_MATCHES)).isEqualTo(1)
        assertThat(result.count(LottoRank.FIVE_MATCHES)).isEqualTo(1)
        assertThat(result.count(LottoRank.SIX_MATCHES)).isEqualTo(1)
    }

    @Test
    fun `당첨 결과의 총 당첨금을 계산한다`() {
        val result =
            LottoResult.from(
                winningTickets(),
                WinningNumbers.from(listOf(1, 2, 3, 4, 5, 6)),
            )

        assertThat(result.totalPrize).isEqualTo(2_001_555_000L)
    }

    @Test
    fun `당첨 번호 5개 일치 결과를 보너스 번호로 2등과 3등으로 구분한다`() {
        val tickets =
            listOf(
                LottoTicket.from(listOf(1, 2, 3, 4, 5, 7)),
                LottoTicket.from(listOf(1, 2, 3, 4, 5, 8)),
            )
        val winningNumbers = WinningNumbers.from(listOf(1, 2, 3, 4, 5, 6), bonusNumber = 7)

        val result = LottoResult.from(tickets, winningNumbers)

        assertThat(result.count(LottoRank.FIVE_MATCHES_WITH_BONUS)).isEqualTo(1)
        assertThat(result.count(LottoRank.FIVE_MATCHES)).isEqualTo(1)
        assertThat(result.totalPrize).isEqualTo(31_500_000L)
    }

    private fun winningTickets(): List<LottoTicket> =
        listOf(
            LottoTicket.from(listOf(1, 2, 3, 10, 11, 12)),
            LottoTicket.from(listOf(1, 2, 3, 4, 11, 12)),
            LottoTicket.from(listOf(1, 2, 3, 4, 5, 12)),
            LottoTicket.from(listOf(1, 2, 3, 4, 5, 6)),
            LottoTicket.from(listOf(1, 2, 10, 11, 12, 13)),
        )
}
