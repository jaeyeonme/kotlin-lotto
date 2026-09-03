package lotto.domain

object RandomLottoNumberGenerator : LottoNumberGenerator {
    private val numberRange = 1..45

    override fun generate(): List<Int> = numberRange.shuffled().take(6).sorted()
}
