# ThinkingBinding - 독서 리뷰 커뮤니티

![너와 나의](https://user-images.githubusercontent.com/106158921/197390643-adfcd205-2423-458f-9ed0-2eead9f891fc.png)


## 📚여러 사람들과 책의 감상을 공유하고 엮어보는 공간📚

***

## 📖기획 배경과 목적

### 오프라인 모임이 부담스럽지만 다른 사람들의 생각도 궁금한 독서가들 모여라!

- 책을 읽다 남들은 이 구절에서 어떤 기분을 느꼈을까 궁금해졌다.
- 이 책을 좋아하는 사람들은 또 어떤 책을 좋아할지 궁금하다.
- 다른 사람들은 어떤 식으로 독서를 하는지 궁금하다.

### 나의 생각 너의 생각 모아서 엮어보기

- 독서 후 느낀점, 자신의 생각등을 서로 공유하고 책을 추천해주는 커뮤니티 만들기
- 자신만의 독서 방법이나 팁등을 공유하는 커뮤니티 만들기

***

## ⚙️ERD
![erd](https://user-images.githubusercontent.com/106158921/197390792-53aaabf2-6094-42d6-baaf-82837f46d3c0.JPG)
***


## 👩‍🏫사용 기술 스택

| BackEnd | FrontEnd | Database | Test |
| --- | --- | --- | --- |
| Spring Boot | Thymeleaf | MySQL | JUnit |
| Gradle | HTML & CSS | JPA |  |
| Spring Security |  JavaScript |  |  |

***
## 💻프로젝트 기능
- 회원 기능
    - 일반 회원
        - 회원 가입 / 로그인 / 로그아웃 / 회원 탈퇴 / 마이페이지 정보 수정
        - 책 리뷰 작성 / 댓글 작성 / 책 찜하기 / 리뷰 좋아요 / 타 회원 구독하기
    - 관리자
        - 책 정보 정리 / 회원 관리 / 카테고리 관리 등
- 게시판 기능
    - 책 게시판
        - 관리자만 글 작성 가능
        - 일반 회원은 책 찜하기를 하면 마이페이지에서 본인이 찜한 책 확인 가능
        - 이 책 리뷰 작성하기 기능을 사용하여 리뷰를 작성
        - 카테고리 별로 책 검색
        - 작성한 글을 언제든 수정, 삭제 가능
    - 리뷰 게시판
        - 일반 회원 글 작성 가능
        - 댓글 작성 가능
        - 좋아요를 누르면 게시글의 좋아요 수가 올라가고 일반회원은 마이페이지에서 본인이 좋아요 누른 리뷰를 확인 가능
        - 글을 작성한 유저는 언제든 글을 수정, 삭제 가능
- 구독 기능
    - 일반 회원끼리는 서로를 구독할 수 있다.
    - 구독한 회원의 글은 자신의 피드 상단에 표시된다.
    - 시간이 된다면 알림 기능도 추가

![test coverage](.github/badges/jacoco.svg)