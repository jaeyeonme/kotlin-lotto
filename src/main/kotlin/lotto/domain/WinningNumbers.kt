package lotto.domain

class WinningNumbers private constructor(
    private val lottoNumbers: LottoNumbers,
    private val bonusNumber: LottoNumber,
) {
    internal fun contains(number: Int): Boolean = LottoNumber(number) in lottoNumbers.values

    internal fun matchesBonus(number: Int): Boolean = LottoNumber(number) == bonusNumber

    companion object {
        fun from(
            numbers: Collection<Int>,
            bonusNumber: Int,
        ): WinningNumbers {
            val lottoNumbers = LottoNumbers.from(numbers.map(::LottoNumber))
            val lottoBonusNumber = LottoNumber(bonusNumber)
            validateDuplicate(lottoNumbers, lottoBonusNumber)
            return WinningNumbers(lottoNumbers, lottoBonusNumber)
        }

        private fun validateDuplicate(
            lottoNumbers: LottoNumbers,
            bonusNumber: LottoNumber,
        ) {
            require(bonusNumber !in lottoNumbers.values) { "보너스 번호는 당첨 번호와 중복될 수 없습니다." }
        }
    }
}
