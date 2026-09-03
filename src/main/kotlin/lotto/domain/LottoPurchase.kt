package lotto.domain

class LottoPurchase(
    private val purchaseAmount: PurchaseAmount,
    manualTickets: List<LottoTicket>,
    automaticTickets: List<LottoTicket>,
) {
    val amount: Int
        get() = purchaseAmount.value

    val manualTicketCount: TicketCount = TicketCount(manualTickets.size)
    val automaticTicketCount: TicketCount = TicketCount(automaticTickets.size)
    val tickets: List<LottoTicket> = (manualTickets + automaticTickets).toList()
}
