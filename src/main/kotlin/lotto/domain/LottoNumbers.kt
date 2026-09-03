package lotto.domain

internal class LottoNumbers private constructor(
    val values: List<Int>,
) {
    companion object {
        private const val SIZE = 6
        private val RANGE = 1..45

        fun from(numbers: Collection<Int>): LottoNumbers {
            require(numbers.size == SIZE) { "로또 번호는 ${SIZE}개여야 합니다." }
            require(numbers.distinct().size == SIZE) { "로또 번호는 중복될 수 없습니다." }
            require(numbers.all { it in RANGE }) { "로또 번호는 ${RANGE.first}부터 ${RANGE.last} 사이여야 합니다." }
            return LottoNumbers(numbers.sorted())
        }
    }
}
