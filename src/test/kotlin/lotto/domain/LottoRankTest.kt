package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoRankTest {
    @Test
    fun `당첨 번호 5개와 보너스 번호가 일치하면 2등이다`() {
        val rank = LottoRank.from(matchCount = 5, matchesBonus = true)

        assertThat(rank).isEqualTo(LottoRank.FIVE_MATCHES_WITH_BONUS)
        assertThat(rank?.prize).isEqualTo(30_000_000)
    }

    @Test
    fun `당첨 번호 5개만 일치하면 3등이다`() {
        val rank = LottoRank.from(matchCount = 5, matchesBonus = false)

        assertThat(rank).isEqualTo(LottoRank.FIVE_MATCHES)
        assertThat(rank?.prize).isEqualTo(1_500_000)
    }
}
