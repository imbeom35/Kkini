# 📢 Branch Convention

## 📌 기본 구조
- Master - Develop - {Backend, Frontend}로 기본 브랜치가 구성되어 있어요.
- 각 기본 브랜치는 다음과 같아요.
  - Master: 배포용 브랜치
  - Develop: 통합 테스트용 브랜치
  - Backend: 백엔드 개발용 브랜치
  - Frontend: 프론트엔드 개발용 브랜치
- 절대로 기본 브랜치에 직접 커밋하면 안돼요.

## 📌 개인 개발 브랜치
- 모든 개발자는 각자 역할에 따라 Backend와 Frontend에서 브랜치를 생성하여 사용해요.
- 브랜치 이름은 다음 형식으로 생성해요.
  - #{이슈번호}/{이슈 요약}
  - ex) #S09P12C210-1/아이디어 세부 회의
- 다른 사람의 브랜치에서 작업하지 않도록 주의해요.

## 📌 Backend/Frontend Branch로의 Pull Request
- 개인 개발 브랜치의 목적을 달성하면 Backend/Frontend 브랜치에 Pull Request를 생성해요.
- 정상적인 실행이 가능한 상태에서만 PR을 생성할 수 있어요.
- 담당 개발자는 다음과 같아요. (변경할 사람 -> 확인할 사람)
  - 백엔드: 용현 -> 범창, 범창 -> 병욱, 병욱 -> 용현
  - 프론트엔드: 태규 -> 승영, 승영 -> 승태, 승태 -> 태규
- 담당 개발자는 해당 PR을 다음 항목에 대해 리뷰해요.
  - 브랜치의 목적을 달성했는가?
  - 불필요하거나 이상한 코드가 존재하는가?
  - 가독성이 좋은가?
  - 코드의 구성과 전개 과정이 이해하기 쉬운가?
- 담당 개발자가 Approve하면 브랜치 담당자가 Backend/Frontend 브랜치에 직접 Merge하고 작업한 브랜치를 삭제해요.
- Jira에서 작업 완료된 이슈의 상태를 Done으로 변경해요.

## 📌 Develop Branch로의 Pull Request
- 특정 기능을 Backend/Frontend 브랜치에서 모두 구현하면 Develop 브랜치에 동시에 Pull Request를 생성해요.
- _**모든**_ 개발자는 자신의 역할에 해당하는 PR을 다음 항목에 대해 리뷰해요.
  - 브랜치의 목적을 달성했는가?
  - 불필요하거나 이상한 코드가 존재하는가?
  - 가독성이 좋은가?
  - 코드의 구성과 전개 과정이 이해하기 쉬운가?
  - BE/FE간 진행 속도가 달라서 생기는 에러는 없을까?
- ***3명 이상***의 개발자가 Approve하면 BE/FE Leader가 Develop 브랜치에 Merge하고 ***로컬 환경***의 동작을 확인해요.
- 서비스가 정상적으로 동작하면 Backend/Frontend 브랜치를 최신화해요.

## 📌 Master Branch로의 Pull Request
- 대분류에 해당하는 기능을 Backend/Frontend 브랜치에서 한 기능을 모두 구현하면 Develop 브랜치에 동시에 Pull Request를 생성해요.
- _**모든**_ 개발자는 해당 PR을 다음 항목에 대해 리뷰해요.
  - 대분류에 해당하는 기능이 정말로 완성되었는가?
  - CI/CD 과정에서 발생하는 문제점은 없을까?
- _**모든**_ 개발자가 Approve하면 Team Leader가 Master 브랜치에 Merge하고 ***실제 서비스***의 동작을 확인해요.
- CD(Continuous Delivery)는 이 PR이 Merge되었을 때 수행돼요.
