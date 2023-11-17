
![main](https://github.com/ZZOL-zzol/sizzang/assets/116516400/f365e28a-3fa4-438c-81ab-01b9397065a2)


## 목차
1. 프로젝트 소개
2. 설계
3. 주요 기능
4. 시연
5. 개발환경
6. 팀원소개 및 개발기간

## 프로젝트 소개

### 서비스 소개
장애인 사용자가 도움을 요청하면, 주변 사용자들에게 알림을 통해 도움이 필요한 상황을 알려주고, 도와줄 의사가 있는 사용자를 매칭해주는 실시간 도움 매칭 서비스입니다.

### 기획 배경
오타니 쇼헤이의 "행운을 주워 담는 행위"에서 착안하여 선행을 베풀면 그 행위가 다시 사회에 남아 나에게 돌아 온다는 것을 컨셉으로 사용자들이 부담없이 도움을 주고 받을 수 있는 매개체를 만드는 것을 목표하였습니다.

<img src="exec/capture/research1.PNG" width="250"/>
<img src="exec/capture/research2.PNG" width="250"/>

### RESEARCH
2022년 장애인이 겪는 고용차별, 직장생활 내 차별을 스스로 헤쳐가는 드라마 '이상한 변호사 우영우'의 등장으로 많은 사람들에게 장애에 대한 인식을 다시 생각해볼 수 있는 한 해가 되었습니다. 하지만 해당 우영우는 판타지라는 의견도 분분합니다. 이유인 즉슨, 대형 로펌의 동료 직원들에게 도움을 받지만, 현실은 우영우처럼 장애인 스스로 현실을 극복하기 어려운 상황들이 여러 직면하기 때문입니다. 특히나 거리에는 장애인이 쉽게 보이지 않는데 이유는 장애인에게 선의를 베푸는 것을 봉사활동이라고 생각하는 사회적 풍토가 만연하기 때문입니다. 저희 A509팀은 일상생활에서의 선의가 봉사라는 의미가 아닌 '공존'이라는 의미를 상기시켜 특별함이 아닌 일상생활에서의 관계맺기 및 도움요청 어플리케이션을 개발하였습니다.

### 페르소나 분석

1. 

### 서비스 특징
- 부담없는 도움 요청
  <br> 장애인에 대한 인식이 두려워 간단한 도움도 쉽게 요청하지 못하는 장애인이 부담없이 도움을 요청할 수 있도록 하였습니다.

- 쉬운 도움 상황 인식
  <br> 주변에 관심을 두지 않거나 어떤 어려움이 있는지 모르는 비장애인들이 도움이 필요한 상황을 쉽게 인식할 수 있도록 하였습니다.

- 행운 컨텐츠 제공으로 사용자 선행 유도
  <br> 포춘 쿠키 및 클로버 지급, 캐릭터 성장 컨텐츠로 도움을 주는 사용자가 선행을 수치화 할 수 있고, 선행을 쌓아가는 데 즐거움을 느낄 수 있도록 하였습니다.

## 설계
### 시스템 아키텍처
<img src="exec/capture/a509.drawio.png"/>

### ERD
![ERD](https://github.com/ZZOL-zzol/sizzang/assets/116516400/906914ab-417a-4755-8b96-0e22394a74e7)

### 요구사항 명세서
![요구사항 명세서](https://github.com/ZZOL-zzol/sizzang/assets/116516400/f234deda-b7c7-43ac-bfb9-aa05c8b5c82a)

### API 명세서
![API 명세서](https://github.com/ZZOL-zzol/sizzang/assets/116516400/2c2f9de3-a079-41ee-a2bb-c546983e43cd)

## 주요 기능
- 장애인 인증
  - 가입 시 장애인 증명서를 통한 장애인 인증
  - 최초 인증 1회로 간편하게 서비스 이용 가능
    <br><br>
- 거리기반 매칭
  - 거리 기반 도움 매칭으로 가까운 위치부터 신속한 사용자 매칭 가능
  - 상황을 명시, 도움을 줄 의사가 있는 사용자가 매칭되므로 부담없는 도움 요청 가능
    <br><br>
- 지도 (실시간 거리 안내)
  - 사용자의 위치 파악 가능
  - 상황을 명시, 도움을 줄 의사가 있는 사용자가 매칭되므로 부담없는 도움 요청 가능
    <br><br>
- 도움 카테고리화
  - 도움 내용을 카테고리화하여 보다 간편한 요청이 가능
  - 사용자 기반 카테고리 추천으로 다양한 도움 요청 가능
    <br><br>
- 채팅 기능
  - 사용자 매칭 후 채팅으로 보다 상세한 요청 및 상황 공유 가능
    <br><br>
- 행운 캐릭터 제공
  - 도움 요청 후 지급되는 클로버로 캐릭터를 성장시키며 행운을 모아가는 것에 재미를 느끼도록 함
    <br><br>
- 포춘 쿠키 제공
  - 도움 요청 후 행운메시지가 담긴 쿠키를 제공, 도움 후 행운 메시지를 통한 사용자 격려


## 시연

### 홈
<img src="exec/capture/홈.jpg" width="250"/>

### 지도
- 도움 요청 현황이 핀으로 표시되며 각 핀을 누르면 도움 내용을 상세히 볼 수 있습니다. 
- 매칭 시에는 상대방의 위치까지 길안내를 제공합니다. 
<img src="exec/capture/지도.jpg" width="250"/>

### 사이드바
- 사이드바를 통해 메뉴를 한눈에 모아볼 수 있습니다.

<img src="exec/capture/사이드바.jpg" width="250"/>

### 카테고리 선택
- 도움을 카테고리화 하여 제공합니다.
- 최근 사용 카테고리를 조회할 수 있습니다. 
- 비슷한 유저들이 사용한 카테고리를 추천받을 수 있습니다.

<img src="exec/capture/카테고리선택.jpg" width="250"/>

### 마이페이지
- 보유한 클로버 확인이 가능합니다. 
- 알림을 모아볼 수 있으며, 프로필 설정이 가능합니다.
- 최근 완료된 도움 이력을 확인할 수 있습니다.

<img src="exec/capture/몬스터.jpg" width="250"/>

### 몬스터
- 도움을 통해 지급받은 클로버로 동물을 성장 시킬 수 있습니다.
- 육지동물, 해양동물, 조류 3가지 유형의 동물을 성장시킬 수 있습니다. 

<img src="exec/capture/마이페이지.jpg" width="250"/>


### 도감
- 각 유형마다 레벨업을 통해 새로운 동물을 지급받을 수 있고, 도감기능으로 동물을 모아볼 수 있습니다.
- 도감이 활성화 된 경우 대표 캐릭터로 등록도 가능합니다.

<img src="exec/capture/도감.jpg" width="250"/>

### 도움 요청
- 카테고리, 요청내용, 위치를 입력하여 도움 요청을 합니다.

<img src="exec/capture/도움요청.jpg" width="250"/>

### 매칭 대기
- 요청 완료 시 주변 사용자들을 탐색하고 사용자들에게 요청 내용을 전송합니다. 
- 탐색 시 주변에 사용자가 없는 경우 50m 단위로 범위를 넓혀가며 탐색을 진행합니다.
- 사용자는 탐색 중 요청을 취소할 수 있으며 1분 이내에 요청에 응한 사용자가 없을 경우 매칭에 실패합니다.

<img src="exec/capture/매칭대기.jpg" width="250"/>

### 도움 내용
- 사용자는 핀 위치를 클릭하여 상세한 도움 요청 내용을 확인할 수 있습니다.
- 연결하기 버튼을 통해 도움 요청에 응할 수 있습니다.
- 다시 지도부분을 눌러 요청에 응하지 않거나 다른 요청을 확인할 수 있습니다.

<img src="exec/capture/도움내용.jpg" width="250"/>

### 채팅
- 매칭이 완료된 경우 사용자간 채팅 방이 생성됩니다.
- 채팅을 통해 도움에 대한 상세한 정보를 확인할 수 있습니다.

<img src="exec/capture/채팅.jpg" width="250"/>

### 도움중
- 도움이 진행 중인 경우 취소를 누를 수 있습니다. 
- 완료 시에는 완료 버튼을 눌러 도움을 완료할 수 있습니다.

<img src="exec/capture/도움중.jpg" width="250"/>


### 포춘쿠키
- 도움이 완료된 경우 포춘쿠키를 지급받을 수 있습니다. 
- 포춘쿠키는 오픈 시 랜덤 행운 메시지를 확인할 수 있으며 확인시 포춘쿠키는 소멸됩니다.

<img src="exec/capture/포춘쿠키.jpg" width="250"/>

### 소소몬 키우기
- 마이페이지에서 귀여운 소소몬의 애니메이션을 통해 소소몬을 키워나갈 수 있습니다.
<img src="exec/capture/sosomon-nurture.mp4" width="250"/>
<img src="exec/capture/feed-clover.mp4" width="250"/>

### 관리자 페이지
- 어플리케이션에서 신고한 사용자들의 신고내용 및 신고 누적을 볼 수 있습니다.

<img src="exec/capture/admin1.PNG" width="250"/>
<img src="exec/capture/admin2.PNG" width="250"/>


## 개발환경
### FrontEnd
![Node.js](https://camo.githubusercontent.com/ab61fce6586c27e04d8ac35d0a77a20b78eb57de63ac2243353f23d3752b1fc3/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4e6f64652e6a732d3333393933333f7374796c653d666f722d7468652d6261646765266c6f676f3d4e6f64652e6a73266c6f676f436f6c6f723d7768697465)
![NPM](https://img.shields.io/badge/NPM-%23CB3837.svg?style=for-the-badge&logo=npm&logoColor=white)
![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)
![React Native](https://img.shields.io/badge/react_native-%2320232a.svg?style=for-the-badge&logo=react&logoColor=%2361DAFB)
![svelte](https://img.shields.io/badge/svelte-FF3E00.svg?style=for-the-badge&logo=svelte&logoColor=white)
![threedotjs](https://img.shields.io/badge/threedotjs-000000.svg?style=for-the-badge&logo=threedotjs&logoColor=white)
![sentry](https://img.shields.io/badge/sentry-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)
![electron](https://img.shields.io/badge/electron-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)
![zustand](https://img.shields.io/badge/zustand-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)
![IntelliJ](https://camo.githubusercontent.com/d479352761a86806b779129f4be8909d1c8c1fb1e2805bbd86cacd276f831cfa/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f496e74656c6c696a5f494445412d3337373641423f7374796c653d666f722d7468652d6261646765266c6f676f3d496e74656c6c696a49444541266c6f676f436f6c6f723d7768697465)
![Android Studio](https://img.shields.io/badge/Android_Studio-%232022a.svg?style=for-the-badge&logo=androidstudio&logoColor=white)

### BackEnd
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://camo.githubusercontent.com/d04cac57f1f6d0a39ebd084333a6e4d93081a42c9e5aa1b3b511e75ad1a1e20f/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f537072696e675f426f6f742d3644423333463f7374796c653d666f722d7468652d6261646765266c6f676f3d537072696e67426f6f74266c6f676f436f6c6f723d7768697465)
![Gradle](https://camo.githubusercontent.com/ce0bfcaef68659861b497d6dfc5b8b783c2955705472b4e55151f196347d9271/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f477261646c652d4633373434303f7374796c653d666f722d7468652d6261646765266c6f676f3d477261646c65266c6f676f436f6c6f723d7768697465)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?&style=for-the-badge&logo=mysql&logoColor=white)
![Redis](https://img.shields.io/badge/redis-DC382D.svg?&style=for-the-badge&logo=redis&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Python](https://img.shields.io/badge/Python-EF2D5E?style=for-the-badge&logo=Python&logoColor=white)
![Django](https://img.shields.io/badge/Django-092E20?style=for-the-badge&logo=Django&logoColor=white)


### Infra
![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)
![Ubuntu](https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white)
![CloudFlare CDN](https://img.shields.io/badge/cloudflare-%23F38020.svg?&style=for-the-badge&logo=cloudflare&logoColor=white)
![Nginx](https://img.shields.io/badge/nginx-%23009639.svg?style=for-the-badge&logo=nginx&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Jenkins](https://camo.githubusercontent.com/9cadc6063746e385b3916ea6ee991ec8eea88306de9208ccf5a94111c0ddf6ee/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4a656e6b696e732d4432343933393f7374796c653d666f722d7468652d6261646765266c6f676f3d4a656e6b696e73266c6f676f436f6c6f723d7768697465)
![apachekafka](https://img.shields.io/badge/apachekafka-231F20.svg?style=for-the-badge&logo=apachekafka&logoColor=white)


### Development Tool
![IntelliJ](https://camo.githubusercontent.com/d479352761a86806b779129f4be8909d1c8c1fb1e2805bbd86cacd276f831cfa/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f496e74656c6c696a5f494445412d3337373641423f7374796c653d666f722d7468652d6261646765266c6f676f3d496e74656c6c696a49444541266c6f676f436f6c6f723d7768697465)
![VisualStudioCode](https://img.shields.io/badge/visual_studio_code-007ACC?style=for-the-badge&logo=visualstudiocode&logoColor=white)
![AndroidStudio](https://img.shields.io/badge/android_studio-3DDC84?style=for-the-badge&logo=visualstudiocode&logoColor=white)

### Communication Tool
![Mattermost](https://camo.githubusercontent.com/04ce7d705b23f2f899a4acd58de46152ea9ab352ce310182432c59db1bd3e74e/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4d61747465724d4f53542d3030393638383f7374796c653d666f722d7468652d6261646765266c6f676f3d4d61747465726d6f7374266c6f676f436f6c6f723d7768697465)
![Jira](https://img.shields.io/badge/jira-%230A0FFF.svg?style=for-the-badge&logo=jira&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![GitLab](https://camo.githubusercontent.com/cb99570e6da369466c9991c5400a2516cec86a958fc80bc152dcdc020b5e581f/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6769746c61622d4643364432363f7374796c653d666f722d7468652d6261646765266c6f676f3d4769744c6162266c6f676f436f6c6f723d7768697465)
![Notion](https://camo.githubusercontent.com/e6016a8747f69a4f7c5cac44f04f81136a1294f2973f25a8d4c53651337a3d78/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4e6f74696f6e2d4546313937303f7374796c653d666f722d7468652d6261646765266c6f676f3d4e6f74696f6e266c6f676f436f6c6f723d7768697465)
![Figma](https://img.shields.io/badge/figma-%23F24E1E.svg?style=for-the-badge&logo=figma&logoColor=white)


## 팀원소개 및 개발기간
|김석주|김성재|왕준영|윤태영|정민희|최찬영|
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|Leader<br>Back-end|Back-end|Back-end<br>Infra|Front-end|Back-end|Front-end|


