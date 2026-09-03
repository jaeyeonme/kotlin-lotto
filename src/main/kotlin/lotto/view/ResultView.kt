package lotto.view

import lotto.domain.LottoPurchase
import lotto.domain.LottoRank
import lotto.domain.LottoResult
import java.util.Locale

object ResultView {
    fun printPurchase(purchase: LottoPurchase) {
        println("${purchase.tickets.size}개를 구매했습니다.")
        purchase.tickets.forEach { println(it.numbers) }
        println()
    }

    fun printResult(
        result: LottoResult,
        revenueRate: Double,
    ) {
        println("당첨 통계")
        println("---------")
        LottoRank.entries.forEach { printRank(it, result.count(it)) }
        println("총 수익률은 ${formatRate(revenueRate)}입니다.")
    }

    private fun printRank(
        rank: LottoRank,
        count: Int,
    ) {
        println("${rank.matchCount}개 일치 (${rank.prize}원)- ${count}개")
    }

    private fun formatRate(revenueRate: Double): String = String.format(Locale.US, "%.1f", revenueRate)
}
