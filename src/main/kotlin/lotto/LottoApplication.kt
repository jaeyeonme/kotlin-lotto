package lotto

import lotto.domain.LottoMachine
import lotto.domain.LottoResult
import lotto.domain.RandomLottoNumberGenerator
import lotto.domain.RevenueRateCalculator
import lotto.domain.WinningNumbers
import lotto.view.InputView
import lotto.view.ResultView

fun main() {
    val purchaseAmount = InputView.readPurchaseAmount()
    val manualTicketCount = InputView.readManualTicketCount(purchaseAmount.ticketCount)
    val manualTickets = InputView.readManualTickets(manualTicketCount)
    val purchase = LottoMachine(RandomLottoNumberGenerator).purchaseWithManualTickets(purchaseAmount, manualTickets)
    ResultView.printPurchase(purchase)

    val winningNumbers = InputView.readWinningNumbers()
    val winningNumbersWithBonus = WinningNumbers.from(winningNumbers, InputView.readBonusNumber(winningNumbers))
    val result = LottoResult.from(purchase.tickets, winningNumbersWithBonus)
    val revenueRate = RevenueRateCalculator.calculate(result.totalPrize, purchase.amount)
    ResultView.printResult(result, revenueRate)
}
