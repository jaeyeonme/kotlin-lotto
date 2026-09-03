package lotto.domain

class WinningNumbers private constructor(
    private val lottoNumbers: LottoNumbers,
    private val bonusNumber: Int?,
) {
    internal fun contains(number: Int): Boolean = number in lottoNumbers.values

    internal fun matchesBonus(number: Int): Boolean = number == bonusNumber

    companion object {
        private val RANGE = 1..45

        fun from(numbers: Collection<Int>): WinningNumbers = WinningNumbers(LottoNumbers.from(numbers), null)

        fun from(
            numbers: Collection<Int>,
            bonusNumber: Int,
        ): WinningNumbers {
            val lottoNumbers = LottoNumbers.from(numbers)
            validateRange(bonusNumber)
            validateDuplicate(lottoNumbers, bonusNumber)
            return WinningNumbers(lottoNumbers, bonusNumber)
        }

        private fun validateRange(bonusNumber: Int) {
            require(bonusNumber in RANGE) { "보너스 번호는 ${RANGE.first}부터 ${RANGE.last} 사이여야 합니다." }
        }

        private fun validateDuplicate(
            lottoNumbers: LottoNumbers,
            bonusNumber: Int,
        ) {
            require(bonusNumber !in lottoNumbers.values) { "보너스 번호는 당첨 번호와 중복될 수 없습니다." }
        }
    }
}
