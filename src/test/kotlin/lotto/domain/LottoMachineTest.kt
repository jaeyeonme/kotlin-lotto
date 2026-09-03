package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class LottoMachineTest {
    @Test
    fun `천 원마다 로또 한 장을 발급한다`() {
        val machine = LottoMachine { listOf(1, 2, 3, 4, 5, 6) }

        val purchase = machine.purchase(14_000)

        assertThat(purchase.tickets).hasSize(14)
        assertThat(purchase.amount).isEqualTo(14_000)
    }

    @Test
    fun `주입한 생성기의 번호로 로또를 발급한다`() {
        val machine = LottoMachine { listOf(6, 5, 4, 3, 2, 1) }

        val purchase = machine.purchase(1_000)

        assertThat(purchase.tickets.single().numbers).containsExactly(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun `생성한 번호가 티켓 조건을 위반하면 발급할 수 없다`() {
        val machine = LottoMachine { listOf(1, 2, 3, 4, 5, 5) }

        assertThatThrownBy { machine.purchase(1_000) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}
