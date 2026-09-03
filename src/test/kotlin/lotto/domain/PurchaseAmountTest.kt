package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class PurchaseAmountTest {
    @Test
    fun `구입 금액으로 전체 로또 수를 계산한다`() {
        val purchaseAmount = PurchaseAmount(14_000)

        assertThat(purchaseAmount.ticketCount.value).isEqualTo(14)
    }

    @Test
    fun `구입 금액이 천 원보다 작으면 생성할 수 없다`() {
        assertThatThrownBy { PurchaseAmount(999) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `구입 금액이 천 원 단위가 아니면 생성할 수 없다`() {
        assertThatThrownBy { PurchaseAmount(1_500) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}
