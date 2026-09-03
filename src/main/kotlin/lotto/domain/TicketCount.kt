package lotto.domain

@JvmInline
value class TicketCount(
    val value: Int,
) {
    init {
        require(value >= 0) { "티켓 수는 음수일 수 없습니다." }
    }

    operator fun minus(other: TicketCount): TicketCount {
        require(value >= other.value) { "수동 구매 수는 전체 구매 수를 넘을 수 없습니다." }
        return TicketCount(value - other.value)
    }
}
