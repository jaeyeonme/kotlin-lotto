package lotto.domain

class LottoTicket private constructor(
    private val lottoNumbers: LottoNumbers,
) {
    val numbers: List<Int>
        get() = lottoNumbers.values

    fun matchCount(winningNumbers: WinningNumbers): Int = numbers.count(winningNumbers::contains)

    companion object {
        fun from(numbers: Collection<Int>): LottoTicket = LottoTicket(LottoNumbers.from(numbers))
    }
}
