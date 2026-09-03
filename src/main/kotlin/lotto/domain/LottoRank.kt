package lotto.domain

enum class LottoRank(
    val matchCount: Int,
    val prize: Long,
) {
    THREE_MATCHES(3, 5_000),
    FOUR_MATCHES(4, 50_000),
    FIVE_MATCHES(5, 1_500_000),
    SIX_MATCHES(6, 2_000_000_000),
    ;

    companion object {
        fun from(matchCount: Int): LottoRank? = entries.find { it.matchCount == matchCount }
    }
}
