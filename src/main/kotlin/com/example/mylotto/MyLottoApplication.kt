package com.example.mylotto

import com.example.mylotto.model.LottoNumber
import com.example.mylotto.model.LottoResult
import com.example.mylotto.model.LottoTicket
import com.example.mylotto.model.LottoWinningNumbers
import com.example.mylotto.service.LottoService
import com.example.mylotto.view.InputView
import com.example.mylotto.view.ResultView

fun main() {
    while (true) {
        doLotto()
    }
}

fun doLotto() {
    val lottoService = LottoService()
    val inputView = InputView()
    val resultView = ResultView()

    var lottoTickets: List<LottoTicket>

    while (true) {
        try {
            val purchaseAmount = inputView.readPurchaseAmount()

            // 몇개를 구매할 수 있는지 로그 출력
            println("총 ${purchaseAmount / 1000}개 구매가능")

            // 수동으로 구매할 로또 수 입력받기
            val manualLottoCount = inputView.readManualLottoCount()

            // 수동으로 구매할 로또가 없는 경우 자동으로 처리
            val manualLottoNumbers = if (manualLottoCount > 0) {
                inputView.readManualLottoNumbers(manualLottoCount)
            } else {
                emptyList()
            }

            lottoTickets = lottoService.generateLottoTickets(purchaseAmount)
            resultView.displayPurchasedTickets(lottoTickets)
            break
        } catch (e: Exception) {
            println("다시 입력해주세요")
        }
    }

    while (true) {
        try {
            val winningNumbers: LottoWinningNumbers = LottoWinningNumbers.of(inputView.readWinningNumbers().map(::LottoNumber))
            val result = LottoResult.of(lottoTickets.map { ticket -> lottoService.matchLottoTicket(ticket, winningNumbers) })
            resultView.displayWinningStatistics(result)
            break
        } catch (e: Exception) {
            println("다시 입력해주세요")
        }
    }

    println()
}
