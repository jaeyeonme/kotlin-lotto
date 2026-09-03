package lotto.domain

class LottoPurchase(
    val amount: Int,
    tickets: List<LottoTicket>,
) {
    val tickets: List<LottoTicket> = tickets.toList()
}
