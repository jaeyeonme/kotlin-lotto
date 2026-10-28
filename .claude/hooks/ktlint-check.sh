#!/usr/bin/env bash
# Silent Success: 코틀린 파일을 고친 뒤 ktlint가 통과하면 아무것도 출력하지 않는다.
# 위반이 있으면 종료 코드 2로 내용을 Claude에게 전달해 바로 고치게 한다.
set -u

file=$(jq -r '.tool_input.file_path // empty')
case "$file" in
  *.kt | *.kts) ;;
  *) exit 0 ;;
esac

root="${CLAUDE_PROJECT_DIR:-$PWD}"
cd "$root" || exit 0

if output=$(./gradlew ktlintCheck -q --console=plain 2>&1); then
  exit 0
fi

# 위반 줄만 전달하고, lint가 아닌 실패는 끝부분을 그대로 전달한다.
violations=$(printf '%s\n' "$output" | grep 'Lint error' | sed "s#$root/##")
echo "ktlint 위반: ${file#"$root"/} 수정 후 ./gradlew ktlintCheck가 실패했습니다." >&2
printf '%s\n' "${violations:-$(printf '%s\n' "$output" | tail -20)}" | head -30 >&2
exit 2
