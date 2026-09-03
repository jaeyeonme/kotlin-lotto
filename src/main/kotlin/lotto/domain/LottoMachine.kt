package lotto.domain

class LottoMachine(
    private val numberGenerator: LottoNumberGenerator,
) {
    fun purchase(amount: Int): LottoPurchase = purchaseWithManualTickets(PurchaseAmount(amount), emptyList())

    fun purchaseWithManualTickets(
        purchaseAmount: PurchaseAmount,
        manualTickets: List<LottoTicket>,
    ): LottoPurchase {
        val manualTicketCount = TicketCount(manualTickets.size)
        val automaticTicketCount = purchaseAmount.ticketCount - manualTicketCount
        val automaticTickets = createAutomaticTickets(automaticTicketCount)
        return LottoPurchase(purchaseAmount, manualTickets, automaticTickets)
    }

    private fun createAutomaticTickets(ticketCount: TicketCount): List<LottoTicket> = List(ticketCount.value) { createTicket() }

    private fun createTicket(): LottoTicket = LottoTicket.from(numberGenerator.generate())
}
