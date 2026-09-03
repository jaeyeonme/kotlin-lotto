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
    val purchase = LottoMachine(RandomLottoNumberGenerator).purchase(purchaseAmount)
    ResultView.printPurchase(purchase)

    val winningNumbers = WinningNumbers.from(InputView.readWinningNumbers())
    val result = LottoResult.from(purchase.tickets, winningNumbers)
    val revenueRate = RevenueRateCalculator.calculate(result.totalPrize, purchase.amount)
    ResultView.printResult(result, revenueRate)
}
