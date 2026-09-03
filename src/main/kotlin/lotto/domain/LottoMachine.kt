package lotto.domain

class LottoMachine(
    private val numberGenerator: LottoNumberGenerator,
) {
    fun purchase(amount: Int): LottoPurchase {
        require(amount >= TICKET_PRICE) { "구입 금액은 로또 가격 이상이어야 합니다." }
        require(amount % TICKET_PRICE == 0) { "구입 금액은 로또 가격 단위여야 합니다." }
        val tickets = List(amount / TICKET_PRICE) { createTicket() }
        return LottoPurchase(amount, tickets)
    }

    private fun createTicket(): LottoTicket = LottoTicket.from(numberGenerator.generate())

    companion object {
        const val TICKET_PRICE = 1_000
    }
}
