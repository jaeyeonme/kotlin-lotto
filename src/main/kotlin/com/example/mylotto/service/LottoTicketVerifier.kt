package com.example.mylotto.service

import com.example.mylotto.constant.LottoConstant

class LottoTicketVerifier {

    // 로또 구매 금액에 대한 유효성 검증
    fun verifyPurchaseAmount(amount: Long) {
        require(amount > 0) { "Purchase amount must be positive." }
        require(amount % LottoConstant.LOTTO_TICKET_PRICE == 0L) {
            "Purchase amount must be a positive multiple of ${LottoConstant.LOTTO_TICKET_PRICE}."
        }

        // 몇개를 구매할 수 있는지 로그 출력
        println("총 ${amount / LottoConstant.LOTTO_TICKET_PRICE}개 구매가능")
    }
}