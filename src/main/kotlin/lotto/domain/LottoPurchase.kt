package lotto.domain

class LottoPurchase(
    private val purchaseAmount: PurchaseAmount,
    tickets: List<LottoTicket>,
) {
    val amount: Int
        get() = purchaseAmount.value

    val tickets: List<LottoTicket> = tickets.toList()
}
