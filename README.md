# Kotlin Lotto

[NEXTSTEP 로또 미션](https://github.com/next-step/kotlin-lotto)을 Kotlin으로 구현한 콘솔 애플리케이션입니다. 구입 금액에 따라 수동·자동 로또를 발급하고, 당첨 번호와 비교해 당첨 결과와 수익률을 출력합니다.

## 주요 기능

- 구입 금액과 수동 구매 수를 입력받아 1장당 1,000원으로 수동·자동 로또를 발급합니다.
- 로또 한 장은 1부터 45까지의 서로 다른 번호 6개로 구성됩니다.
- 잘못된 금액·수량·로또 번호·당첨 번호·보너스 번호는 다시 입력받습니다.
- 당첨 번호 일치 개수와 보너스 번호 일치 여부로 당첨 등수를 판정합니다.
- 수동·자동 구매 수, 발급한 로또, 당첨 통계와 수익률(총 당첨금 ÷ 구입 금액)을 출력합니다.

## 설계

### 자동 번호 생성

`LottoMachine`은 자동 번호를 직접 생성하지 않고 `LottoNumberGenerator`를 통해 번호를 받습니다. 실행에서는 `RandomLottoNumberGenerator`를 사용하고, 테스트에서는 생성할 번호를 지정합니다.

### 번호·구입 금액·티켓 수 검증

`LottoNumber`는 번호 범위를, `LottoNumbers`는 번호 개수와 중복을 검증합니다. `PurchaseAmount`는 최소 구입 금액과 구입 단위를, `TicketCount`는 티켓 수가 음수가 아닌지 검증합니다.

### 당첨 등수 계산

`LottoTicket`은 당첨 번호와 일치하는 번호 개수를 계산하고, `WinningNumbers`는 보너스 번호 일치 여부를 확인합니다. `LottoRank`는 두 결과를 바탕으로 당첨 등수를 결정하고, `LottoResult`는 티켓별 결과와 당첨금을 집계합니다.

## 테스트

전체 테스트와 코드 스타일은 다음 명령으로 확인합니다.

```shell
./gradlew clean build ktlintCheck
```

## 개발 기록

단계별 변경 내용은 Issue와 PR에 기록했습니다.

| 단계 | 내용 | 기록 |
| --- | --- | --- |
| Step 1 | 문자열 계산기 | [Issue #1](https://github.com/jaeyeonme/kotlin-lotto/issues/1) · [PR #2](https://github.com/jaeyeonme/kotlin-lotto/pull/2) |
| Step 2 | 자동 로또 구매 | [Issue #3](https://github.com/jaeyeonme/kotlin-lotto/issues/3) · [PR #4](https://github.com/jaeyeonme/kotlin-lotto/pull/4) |
| Step 2-1 | 수익률 계산 수정 | [Issue #5](https://github.com/jaeyeonme/kotlin-lotto/issues/5) · [PR #6](https://github.com/jaeyeonme/kotlin-lotto/pull/6) |
| Step 3 | 보너스 번호와 당첨 판정 | [Issue #7](https://github.com/jaeyeonme/kotlin-lotto/issues/7) · [PR #8](https://github.com/jaeyeonme/kotlin-lotto/pull/8) |
| Step 4 | 수동·자동 로또 구매 | [Issue #9](https://github.com/jaeyeonme/kotlin-lotto/issues/9) · [PR #10](https://github.com/jaeyeonme/kotlin-lotto/pull/10) |

## 실행 방법

JDK 25 환경에서 IDE로 `LottoApplication.kt`의 `main` 함수를 실행합니다.

실행 후 구입 금액, 수동 구매 수, 수동 번호, 지난주 당첨 번호, 보너스 번호를 차례대로 입력합니다. 자동 번호는 구매할 때 무작위로 생성됩니다.
