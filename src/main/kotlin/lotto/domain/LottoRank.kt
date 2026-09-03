package lotto.domain

enum class LottoRank(
    val matchCount: Int,
    val prize: Long,
    val description: String,
) {
    THREE_MATCHES(3, 5_000, "3개 일치"),
    FOUR_MATCHES(4, 50_000, "4개 일치"),
    FIVE_MATCHES(5, 1_500_000, "5개 일치"),
    FIVE_MATCHES_WITH_BONUS(5, 30_000_000, "5개 일치, 보너스 볼 일치"),
    SIX_MATCHES(6, 2_000_000_000, "6개 일치"),
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
