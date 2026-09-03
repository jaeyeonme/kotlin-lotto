package lotto.view

import lotto.domain.LottoTicket
import lotto.domain.PurchaseAmount
import lotto.domain.TicketCount
import lotto.domain.WinningNumbers

object InputView {
    fun readPurchaseAmount(): PurchaseAmount {
        println(PURCHASE_AMOUNT_PROMPT)
        return readValue(::parsePurchaseAmount)
    }

    fun readManualTicketCount(totalTicketCount: TicketCount): TicketCount {
        println(MANUAL_TICKET_COUNT_PROMPT)
        return readValue { input -> parseManualTicketCount(input, totalTicketCount) }
    }

    fun readManualTickets(ticketCount: TicketCount): List<LottoTicket> {
        println(MANUAL_NUMBERS_PROMPT)
        return List(ticketCount.value) { readLottoTicket() }
    }

    fun readWinningNumbers(): List<Int> {
        println(WINNING_NUMBERS_PROMPT)
        return readLottoTicket().numbers
    }

    fun readBonusNumber(winningNumbers: List<Int>): Int {
        println(BONUS_NUMBER_PROMPT)
        return readValue { input -> parseBonusNumber(input, winningNumbers) }
    }

    private fun readLottoTicket(): LottoTicket = readValue(::parseLottoTicket)

    private fun <T> readValue(parser: (String?) -> T?): T {
        while (true) {
            parser(readlnOrNull())?.let { return it }
            println(INVALID_INPUT_MESSAGE)
        }
    }

    private fun parsePurchaseAmount(input: String?): PurchaseAmount? =
        input?.toIntOrNull()?.let { amount -> runCatching { PurchaseAmount(amount) }.getOrNull() }

    private fun parseManualTicketCount(
        input: String?,
        totalTicketCount: TicketCount,
    ): TicketCount? =
        input
            ?.toIntOrNull()
            ?.let { count -> runCatching { TicketCount(count) }.getOrNull() }
            ?.takeIf { count -> count.value <= totalTicketCount.value }

    private fun parseLottoTicket(input: String?): LottoTicket? =
        parseNumbers(input)?.let { numbers -> runCatching { LottoTicket.from(numbers) }.getOrNull() }

    private fun parseNumbers(input: String?): List<Int>? {
        val numbers = input?.split(",")?.map(String::trim)?.map(String::toIntOrNull) ?: return null
        return numbers.takeIf { values -> values.all { it != null } }?.filterNotNull()
    }

    private fun parseBonusNumber(
        input: String?,
        winningNumbers: List<Int>,
    ): Int? {
        val bonusNumber = input?.toIntOrNull() ?: return null
        return bonusNumber.takeIf { isValidBonusNumber(winningNumbers, it) }
    }

    private fun isValidBonusNumber(
        winningNumbers: List<Int>,
        bonusNumber: Int,
    ): Boolean = runCatching { WinningNumbers.from(winningNumbers, bonusNumber) }.isSuccess

    private const val PURCHASE_AMOUNT_PROMPT = "구입금액을 입력해 주세요."
    private const val MANUAL_TICKET_COUNT_PROMPT = "수동으로 구매할 로또 수를 입력해 주세요."
    private const val MANUAL_NUMBERS_PROMPT = "수동으로 구매할 번호를 입력해 주세요."
    private const val WINNING_NUMBERS_PROMPT = "지난 주 당첨 번호를 입력해 주세요."
    private const val BONUS_NUMBER_PROMPT = "보너스 볼을 입력해 주세요."
    private const val INVALID_INPUT_MESSAGE = "잘못된 입력입니다. 다시 입력해 주세요."
}
