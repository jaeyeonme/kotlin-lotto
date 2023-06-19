package lotto.domain

import lotto.dto.WinStats
import lotto.util.AutoNumbers

class LottoTickets(private val money: Money, autoNumbers: AutoNumbers) {
    val lottoTickets: List<LottoTicket> = List(money.countLotto()) {
        LottoTicket(autoNumbers.generateNumbers())
    }

    fun getWinStats(winNumbers: LottoTicket): WinStats {
        val matchMap: Map<WinResult, Int> = lottoTickets.map {
            it.getWinResult(winNumbers)
        }.filter {
            it !== WinResult.LOSE
        }.groupingBy { it }
            .eachCount()

        val amount = matchMap.keys
            .map { it.reward }
            .fold(0.0f) { total, num -> total + num }

        return WinStats(matchMap, calculateYield(amount))
    }

    private fun calculateYield(amount: Float): Number {
        if (amount == 0.0f) {
            return 0
        }
        return amount / money.value
    }
}