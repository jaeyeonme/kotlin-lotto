package lotto.domain

@JvmInline
value class TicketCount(
    val value: Int,
) {
    init {
        require(value >= 0) { "티켓 수는 음수일 수 없습니다." }
    }
}
