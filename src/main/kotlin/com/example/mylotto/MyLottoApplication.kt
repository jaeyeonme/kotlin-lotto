package com.example.mylotto

import com.example.mylotto.model.LottoNumber
import com.example.mylotto.model.LottoResult
import com.example.mylotto.model.LottoTicket
import com.example.mylotto.model.LottoWinningNumbers
import com.example.mylotto.service.LottoService
import com.example.mylotto.service.LottoTicketVerifier
import com.example.mylotto.view.InputView
import com.example.mylotto.view.ResultView

fun main() {
    while (true) {
        doLotto()
    }
}

fun doLotto() {
    val lottoService = LottoService()
    var lottoTicketVerifier = LottoTicketVerifier()
    val inputView = InputView()
    val resultView = ResultView()

    var automaticLottoTickets: List<LottoTicket>
    var manualLottoTickets: List<LottoTicket>

    while (true) {
        try {
            val purchaseAmount = inputView.readPurchaseAmount()
            lottoTicketVerifier.verifyPurchaseAmount(purchaseAmount);

            // 수동으로 구매할 로또 수 입력받기
            val manualLottoCount = inputView.readManualLottoCount()

            // 수동으로 구매할 로또가 없는 경우 자동으로 처리
            val inputManualLottoNumbers = if (manualLottoCount > 0) {
                inputView.readManualLottoNumbers(manualLottoCount)
            } else {
                emptyList()
            }

            val automaticLottoCount = (purchaseAmount / 1000).toInt() - manualLottoCount
            automaticLottoTickets = lottoService.generateAutomaticLottoTickets(automaticLottoCount)
            manualLottoTickets = lottoService.generateManualLottoTickets(inputManualLottoNumbers)

            resultView.displayPurchasedTickets(manualLottoTickets, automaticLottoTickets)
            break
        } catch (e: Exception) {
            println("다시 입력해주세요")
        }
    }

    while (true) {
        try {
            val winningNumbers: LottoWinningNumbers = LottoWinningNumbers.of(
                inputView.readWinningNumbers()
                    .map(::LottoNumber)
            )

            // manual 과 automatic 을 함께 처리
            val allTickets = manualLottoTickets + automaticLottoTickets
            val result = LottoResult.of(
                allTickets.map {
                    ticket -> lottoService.matchLottoTicket(ticket, winningNumbers)
                }
            )

            resultView.displayWinningStatistics(result)
            break
        } catch (e: Exception) {
            println("다시 입력해주세요")
        }
    }

    println()
}
