package lotto.domain

class WinningNumbers private constructor(
    private val lottoNumbers: LottoNumbers,
) {
    internal fun contains(number: Int): Boolean = number in lottoNumbers.values

    companion object {
        fun from(numbers: Collection<Int>): WinningNumbers = WinningNumbers(LottoNumbers.from(numbers))
    }
}
