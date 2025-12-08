package com.example.mylotto.view

class InputView {
    fun readPurchaseAmount(): Long {
        println("구입금액을 입력해 주세요.")
        val amount = readlnOrNull()?.toLongOrNull()
        require(amount != null)
        return amount
    }

    // 수동으로 구매할 로또 수를 입력받는 함수
    fun readManualLottoCount(): Int {
        println("수동으로 구매할 로또 수를 입력해 주세요.")
        val count = readlnOrNull()?.toIntOrNull()
        require(count != null)
        return count
    }

    // 수동으로 구매할 로또 번호를 입력받는 함수
    fun readManualLottoNumbers(count: Int): List<List<Int>> {
        println("수동으로 구매할 로또 번호를 입력해 주세요.")
        val manualLottoNumbers = mutableListOf<List<Int>>()
        for (i in 1..count) {
            val numbers =
                readlnOrNull()
                    ?.split(",")
                    ?.mapNotNull { it.trim().toIntOrNull() }
            require(numbers != null)
            manualLottoNumbers.add(numbers)
        }
        return manualLottoNumbers
    }

    fun readWinningNumbers(): List<Int> {
        println("지난 주 당첨 번호를 입력해 주세요.")
        val numbers =
            readlnOrNull()
                ?.split(",")
                ?.mapNotNull { it.trim().toIntOrNull() }
        require(numbers != null)
        return numbers
    }
}
