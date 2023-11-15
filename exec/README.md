# 소소행
- 장애인을 위한 실시간 도움 요청 서비스

## 목차
1. 프로젝트 소개
2. 설계
3. 주요 기능
4. 시연
5. 개발환경
6. 프로젝트 회고
7. 팀원소개 및 개발기간

## 프로젝트 소개

### 서비스 소개
일상 생활에서 마주하는 소소한 불편함에도 도움 요청을 하지 못하고 불편함을 감내하는 장애인들을 대상으로 하는 도움 요청 서비스 입니다. 

### 기획 배경
오타니 쇼헤이의 "행운을 주워 담는 행위"에서 착안하여 선행을 베풀면 그 행위가 다시 사회에 남아 나에게 돌아 온다는 것을 컨셉으로 사용자들이 부담없이 도움을 주고 받을 수 있는 매개체를 만드는 것을 목표하였습니다.

### 서비스 특징
- 부담없는 도움 요청
<br> 장애인에 대한 인식이 두려워 간단한 도움도 쉽게 요청하지 못하는 장애인이 부담없이 도움을 요청할 수 있도록 하였습니다.

- 쉬운 도움 상황 인식
<br> 주변에 관심을 두지 않거나 어떤 어려움이 있는지 모르는 비장애인들이 도움이 필요한 상황을 쉽게 인식할 수 있도록 하였습니다.

- 행운 컨텐츠 제공으로 사용자 선행 유도
<br> 포춘 쿠키 및 클로버 지급, 캐릭터 성장 컨텐츠로 도움을 주는 사용자가 선행을 수치화 할 수 있고, 선행을 쌓아가는 데 즐거움을 느낄 수 있도록 하였습니다.

## 설계
__시스템 아키텍처__
![System Architecture](https://github.com/ZZOL-zzol/sizzang/assets/116516400/52652ab6-204f-4c7a-a02d-2aea3f05408b)
__ERD__
![ERD](https://github.com/ZZOL-zzol/sizzang/assets/116516400/906914ab-417a-4755-8b96-0e22394a74e7)
__요구사항 명세서__
![요구사항 명세서](https://github.com/ZZOL-zzol/sizzang/assets/116516400/b0bd33c3-b60a-4bdf-8039-24b6755d4666)

## 주요 기능
- 장애인 인증
    - 가입 시 장애인 증명서를 통한 장애인 인증
    - 최초 인증 1회로 간편하게 서비스 이용 가능
<br><br>
- 거리기반 매칭
    - 거리 기반 도움 매칭으로 가까운 위치부터 신속한 사용자 매칭 가능
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

## 개발환경
### FrontEnd
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
![Node.js](https://camo.githubusercontent.com/ab61fce6586c27e04d8ac35d0a77a20b78eb57de63ac2243353f23d3752b1fc3/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4e6f64652e6a732d3333393933333f7374796c653d666f722d7468652d6261646765266c6f676f3d4e6f64652e6a73266c6f676f436f6c6f723d7768697465)
![NPM](https://img.shields.io/badge/NPM-%23CB3837.svg?style=for-the-badge&logo=npm&logoColor=white)
![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)
![React Native](https://img.shields.io/badge/react_native-%2320232a.svg?style=for-the-badge&logo=react&logoColor=%2361DAFB)
![svelte](https://img.shields.io/badge/svelte-FF3E00.svg?style=for-the-badge&logo=svelte&logoColor=white)
![threedotjs](https://img.shields.io/badge/threedotjs-000000.svg?style=for-the-badge&logo=threedotjs&logoColor=white)
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

## 프로젝트 회고
### MSA
### Test Code


## 팀원소개 및 개발기간
|김석주|김성재|왕준영|윤태영|정민희|최찬영|
|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|
|테스트1|테스트2|테스트3|테스트1|테스트2|테스트3|
|Leader<br>Back-end|Back-end|Back-end<br>Infra|Front-end|Back-end|Front-end|


