package com.example.mylotto.service

import com.example.mylotto.constant.LottoConstant

class LottoVerifier {

    // 로또 구매 금액에 대한 유효성 검증
    fun verifyPurchaseAmount(amount: Long) {
        require(amount > 0) { "Purchase amount must be positive." }
        require(amount % LottoConstant.LOTTO_TICKET_PRICE == 0L) {
            "Purchase amount must be a positive multiple of ${LottoConstant.LOTTO_TICKET_PRICE}."
        }

        // 몇개를 구매할 수 있는지 로그 출력
        println("총 ${amount / LottoConstant.LOTTO_TICKET_PRICE}개 구매가능")
    }

    // 보너스볼이 지난주 당첨번호에 포함되어 있지 않음을 검증
    fun verifyBonusNumber(bonusNumber: Int, winningNumbers: Set<Int>) {
        require(bonusNumber !in winningNumbers) { "Bonus number must not duplicate winning numbers." }
    }
}