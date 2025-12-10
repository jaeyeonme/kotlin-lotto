package com.example.mylotto.service

import com.example.mylotto.enum.Rank
import com.example.mylotto.model.LottoNumber
import com.example.mylotto.model.LottoTicket
import com.example.mylotto.model.LottoWinningNumbers

class LottoService {
    // LottoTicket 자동구매
    fun generateAutomaticLottoTickets(ticketCount: Int): List<LottoTicket> {
        return List(ticketCount) {
            LottoTicket()
        }
    }

    // LottoTicket 수동구매
    fun generateManualLottoTickets(
        manualLottoNumbers: List<List<Int>>,
    ): List<LottoTicket> {
        return manualLottoNumbers.map { numbers ->
            LottoTicket(numbers.map { number -> LottoNumber(number) }.toSet())
        }
    }

    // LottoTicket 당첨 결과 확인
    fun matchLottoTicket(
        lottoTicket: LottoTicket,
        winningNumbers: LottoWinningNumbers,
    ): Rank {
        // matchCount 계산 시 WinningNumber의 보너스볼을 반영하여 Rank 계산
        val matchedNumbers = lottoTicket.numbers.intersect(winningNumbers.numbers)
        val matchedCount = matchedNumbers.size
        val hasBonusNumber = lottoTicket.numbers.contains(winningNumbers.bonusNumber)

        return Rank.valueOf(matchedCount, hasBonusNumber)
    }
}
