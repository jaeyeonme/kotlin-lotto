package lotto.domain

data class LottoNumber(
    val value: Int,
) {
    init {
        require(value in RANGE) { "로또 번호는 ${RANGE.first}부터 ${RANGE.last} 사이여야 합니다." }
    }

    companion object {
        private val RANGE = 1..45
    }
}
