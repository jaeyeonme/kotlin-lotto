# kotlin-lotto

[NEXTSTEP 로또 미션](https://github.com/next-step/kotlin-lotto)을 Kotlin으로 구현한 콘솔 애플리케이션이다. 기능과 설계는 README.md를 따른다.

## 빌드와 검증

- 전체 검증: `./gradlew clean build ktlintCheck` (JDK 25, Kotlin 2.4.10)
- 테스트만: `./gradlew test`
- 실행 task가 없다(`application` 플러그인 없음). IDE에서 `src/main/kotlin/lotto/LottoApplication.kt`의 `main`을 실행한다.
- 코틀린 파일을 고치면 `.claude/hooks/ktlint-check.sh`가 `ktlintCheck`를 실행한다. 실패하면 위반 줄을 바로 고친다.

## 코드 구성

- `lotto/domain`: 도메인(번호, 구입 금액, 티켓 수, 당첨 판정). 테스트는 `src/test/kotlin/lotto/domain`.
- `lotto/view`: `InputView`, `ResultView`. 자동 테스트가 없다.
- `stringcalculator`: Step 1 문자열 덧셈 계산기. 실행 진입점이 없다.

## Issue·PR·README 형식

이 저장소는 미션 형식을 이어 쓴다. 기본 형식(`설명`·`todo`, `개요`·`구현 결정`)으로 바꾸지 않는다.

- 제목은 미션 공식 단계명 `[Step N] …`을 쓴다. 단계 사이 수정은 `[Step 2-1]`처럼 쓴다.
- Issue: `## Objective`, `## Acceptance Criteria`, `## Verification`, `## Out of Scope`. 미션 제약이 있으면 `## Programming Constraints`를 더한다. 버그 수정 Issue는 `## Steps to Reproduce`, `## Resolution Criteria`를 쓴다.
- PR: `## Summary`, `## Changes`, `## Verification`. 마지막에 `Closes #번호`.
- README는 `/harness:readme` 미션형 프로필의 구성(소개, 주요 기능, 설계, 테스트, 개발 기록, 실행 방법)을 유지한다. 개발 기록 표에는 Issue와 짝을 이루는 PR만 올린다.
