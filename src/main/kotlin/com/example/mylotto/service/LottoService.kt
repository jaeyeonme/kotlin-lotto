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

    fun matchLottoTicket(
        lottoTicket: LottoTicket,
        winningNumbers: LottoWinningNumbers,
    ): Rank {
        val matchedCount = lottoTicket.numbers.intersect(winningNumbers.numbers).size
        return Rank.valueOf(matchedCount)
    }
}
