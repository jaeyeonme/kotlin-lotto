package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RevenueRateCalculatorTest {
    @Test
    fun `총 당첨금과 구입 금액으로 수익률을 계산한다`() {
        val rate = RevenueRateCalculator.calculate(totalPrize = 5_000, purchaseAmount = 10_000)

        assertThat(rate).isEqualTo(50.0)
    }
}
