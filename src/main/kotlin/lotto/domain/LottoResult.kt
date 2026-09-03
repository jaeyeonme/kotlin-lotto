package lotto.domain

class LottoResult private constructor(
    rankCounts: Map<LottoRank, Int>,
) {
    private val rankCounts: Map<LottoRank, Int> = rankCounts.toMap()

    val totalPrize: Long = LottoRank.entries.sumOf { rank -> rank.prize * count(rank) }

    fun count(rank: LottoRank): Int = rankCounts[rank] ?: 0

    companion object {
        fun from(
            tickets: List<LottoTicket>,
            winningNumbers: WinningNumbers,
        ): LottoResult {
            val ranks = tickets.mapNotNull { ticket -> LottoRank.from(ticket.matchCount(winningNumbers)) }
            return LottoResult(ranks.groupingBy { it }.eachCount())
        }
    }
}
