package lotto.domain

object RevenueRateCalculator {
    private const val PERCENT = 100.0

    fun calculate(
        totalPrize: Long,
        purchaseAmount: Int,
    ): Double {
        require(totalPrize >= 0) { "총 당첨금은 음수일 수 없습니다." }
        require(purchaseAmount > 0) { "구입 금액은 0보다 커야 합니다." }
        return totalPrize.toDouble() / purchaseAmount * PERCENT
    }
}
