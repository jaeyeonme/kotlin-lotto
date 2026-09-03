package lotto.domain

enum class LottoRank(
    val matchCount: Int,
    val prize: Long,
) {
    THREE_MATCHES(3, 5_000),
    FOUR_MATCHES(4, 50_000),
    FIVE_MATCHES(5, 1_500_000),
    FIVE_MATCHES_WITH_BONUS(5, 30_000_000),
    SIX_MATCHES(6, 2_000_000_000),
    ;

    companion object {
        fun from(
            matchCount: Int,
            matchesBonus: Boolean = false,
        ): LottoRank? =
            when {
                matchCount == FIVE_MATCHES.matchCount && matchesBonus -> FIVE_MATCHES_WITH_BONUS
                else -> entries.find { it != FIVE_MATCHES_WITH_BONUS && it.matchCount == matchCount }
            }
    }
}
