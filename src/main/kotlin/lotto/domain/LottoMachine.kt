package lotto.domain

class LottoMachine(
    private val numberGenerator: LottoNumberGenerator,
) {
    fun purchase(amount: Int): LottoPurchase = purchaseTickets(PurchaseAmount(amount))

    private fun purchaseTickets(purchaseAmount: PurchaseAmount): LottoPurchase {
        val tickets = List(purchaseAmount.ticketCount.value) { createTicket() }
        return LottoPurchase(purchaseAmount, tickets)
    }

    private fun createTicket(): LottoTicket = LottoTicket.from(numberGenerator.generate())
}
