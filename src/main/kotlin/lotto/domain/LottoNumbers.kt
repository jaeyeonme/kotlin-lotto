package lotto.domain

internal class LottoNumbers private constructor(
    val values: List<LottoNumber>,
) {
    companion object {
        private const val SIZE = 6

        fun from(numbers: Collection<LottoNumber>): LottoNumbers {
            require(numbers.size == SIZE) { "로또 번호는 ${SIZE}개여야 합니다." }
            require(numbers.distinct().size == SIZE) { "로또 번호는 중복될 수 없습니다." }
            return LottoNumbers(numbers.sortedBy(LottoNumber::value))
        }
    }
}
