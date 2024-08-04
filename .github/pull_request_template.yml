name: PR 템플릿
description: 새로운 Pull Request를 제출할 때 이 템플릿을 사용하세요.
title: "[PR] "
labels: ["PR"]
assignees: ["330sum", "lkdcode"]
body:
  - type: input
    id: pr_title
    attributes:
      label: PR 제목
      description: PR의 제목을 작성하세요. (예: "[FEATURE] 새로운 로그인 페이지 추가")
      placeholder: PR 제목
    validations:
      required: true

  - type: textarea
    id: summary
    attributes:
      label: 요약
      description: 이 PR이 무엇을 변경하는지 간략히 설명하세요.
      placeholder: PR 요약
    validations:
      required: true

  - type: textarea
    id: details
    attributes:
      label: 세부 설명
      description: 변경 사항에 대한 자세한 설명을 적어 주세요.
      placeholder: 세부 설명
    validations:
      required: true

  - type: textarea
    id: related_issues
    attributes:
      label: 관련 이슈
      description: 이 PR이 해결하는 이슈 번호를 적어 주세요. (예: Closes #123)
      placeholder: 관련 이슈
    validations:
      required: true

  - type: textarea
    id: test_instructions
    attributes:
      label: 테스트 방법
      description: 변경 사항이 잘 작동하는지 테스트하는 방법을 설명하세요.
      placeholder: 테스트 방법
    validations:
      required: true

  - type: markdown
    attributes:
      value: |
        ### 체크리스트
        PR을 제출하기 전에 아래 항목을 확인하세요:
        - [ ] 코드가 제대로 컴파일되나요?
        - [ ] 모든 테스트가 통과하나요?
        - [ ] 관련 문서를 업데이트했나요?
        - [ ] 변경 사항을 문서화했나요?

  - type: textarea
    id: additional_info
    attributes:
      label: 추가 정보
      description: 이 PR과 관련된 추가 정보를 제공하세요.
      placeholder: 추가 정보
    validations:
      required: false
