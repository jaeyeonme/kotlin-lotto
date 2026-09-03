package lotto.domain

@JvmInline
value class PurchaseAmount(
    val value: Int,
) {
    val ticketCount: TicketCount
        get() = TicketCount(value / TICKET_PRICE)

    init {
        require(value >= TICKET_PRICE) { "구입 금액은 로또 가격 이상이어야 합니다." }
        require(value % TICKET_PRICE == 0) { "구입 금액은 로또 가격 단위여야 합니다." }
    }

    companion object {
        const val TICKET_PRICE = 1_000
    }
}
