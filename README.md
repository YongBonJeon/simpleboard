## Spring Boot 
- 이제는 많이 익숙해진 Spring boot를 통해 프로젝트를 시작해본다.
![](https://velog.velcdn.com/images/bon0057/post/14d4d98d-0c8b-462a-825e-a55568d828f8/image.png)

- Spring boot 3.2.1
- Java 17 
- gradle
- Local : Macbook Air M1

## 코드
[github/simpleboard](https://github.com/YongBonJeon/simpleboard)


## 요구사항
- 도메인
	
    - 이용자
    - 게시글
    - 댓글
- 게시글 관리 기능
	
    - 게시글 등록
    - 게시글 목록 조회
    - 게시글 조회
    - 게시글 삭제
    - 게시글 수정
- 댓글 관리 기능
	
    - 댓글 등록
    - 댓글 조회
    - 댓글 삭제
    - 댓글 수정
- 로그인 기능
	
    - 로그인 전에는 회원가입, 로그인만 가능
    - 로그인 후에 모든 기능 활성화
- 배포 전까지는 H2 DB 사용 (추후 다른 DB 연결 예정)
- 데이터 접근 기술로 기본적인 CRUD는 스프링 데이터 JPA

> 초기 버전에서는 최소한의 기능만 설계

## 홈 디자인
![](https://velog.velcdn.com/images/bon0057/post/77822a2b-203d-4c10-a767-c7555e3a599c/image.png)

- 부트스트랩의 무료 템플릿을 가져와서 적절히 수정할 예정

## 구현 - V1
### 초기 기본 기능 구현 
- 로그인 전 홈화면 : 비로그인 시에는 회원가입, 로그인 기능 외에는 작동하지 않음
![](https://velog.velcdn.com/images/bon0057/post/a7969a19-7b95-419e-9df3-41c30f853766/image.png)
- 회원가입 기능
![](https://velog.velcdn.com/images/bon0057/post/c0d6ff21-361c-43ff-a66d-3515a8d9c060/image.png)
- 로그인 기능 
![](https://velog.velcdn.com/images/bon0057/post/f687ba41-2918-4f60-98e4-0d5712e2c965/image.png)
- 로그인 후 홈 화면 : 회원가입 대신 이름이, 로그인 대신 로그아웃 버튼 활성화 
![](https://velog.velcdn.com/images/bon0057/post/a733bbd0-148a-4163-977f-2169d3571885/image.png)
- 게시글 등록 기능
![](https://velog.velcdn.com/images/bon0057/post/fe610da3-d324-4ab8-94f7-2f1244bbafae/image.png)
- 게시글 상세 : 게시글 수정, 삭제 + 댓글 등록 가능
![](https://velog.velcdn.com/images/bon0057/post/13b76971-7768-4326-b1d1-603b2cf47596/image.png)
- 댓글 등록 후에 댓글 수정, 삭제 가능
![](https://velog.velcdn.com/images/bon0057/post/f33fdc28-a8df-4b28-8e5c-532236d3306f/image.png)
- 전체 게시글 조회
![](https://velog.velcdn.com/images/bon0057/post/f7e90a86-ce47-4288-95aa-e9b2b6499270/image.png)

## 구현 - V2
### JPA를 통한 참조
- 기존 엔티티는 JPA를 통한 참조를 구현하지 않았기에 참조 제대로 구현 
```java
@Data
@Entity
public class Member extends DateEntity{

    @Id @GeneratedValue
    private Long Id;

    private String name;
    private String loginId;
    private String password;
    private String email;
    private String phoneNumber;
    @Embedded
    private Address address;
	
    ...
}

@Data
@Entity
public class Post extends DateEntity{
    @Id @GeneratedValue
    private Long Id;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    ...
}

@Entity
@Data
public class Comment extends DateEntity {

    @Id @GeneratedValue
    private Long Id;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
	
    ...
}
```
![](https://velog.velcdn.com/images/bon0057/post/d8befcf5-2dbd-48da-9572-52d01c329ced/image.png)

### 페이징
![](https://velog.velcdn.com/images/bon0057/post/65c830e2-77ab-40a9-9b11-5e6aa8e965f3/image.png)
- 전체 게시글 조회 시 페이징 구현 

### 내 정보 
![](https://velog.velcdn.com/images/bon0057/post/c232a466-f233-4825-8e2c-426f5e6dcd74/image.png)
- 본인 개인정보 및 작성한 게시글 조회 기능 구현 

### 검색 기능 
![](https://velog.velcdn.com/images/bon0057/post/e97fb575-6725-4ecd-987f-ff0d51bcca79/image.png)
- Like SQL을 사용해서 제목 검색 기능 추가 

## 구현 - V3
### 수정, 삭제 버튼 활성화 / 비활성화 
- 기존의 게시글, 댓글 환경은 본인이 작성한 게시글, 댓글이 아니더라도 수정, 삭제 버튼이 활성화되어 있어 누구나 접근이 가능한 상태
- 현재 로그인되어 있는 유저의 상태를 Session을 통해 확인하여 게시글, 댓글의 작성자와 일치하는지 확인하여 수정, 삭제 버튼 활성화 / 비활성화 
```java
<div th:if="${loginMember.getId() == comment.getMember().getId()}">
```
- 기존의 ERD에선 Comment와 Member을 매핑하지 않아 Comment의 주인을 알아볼 수 없는 상황이라 ERD를 조금 수정(Member 테이블과 Comment 테이블 일대다 매핑)
![](https://velog.velcdn.com/images/bon0057/post/7c5369d1-8b16-47d1-a44c-4a28f3b702e1/image.png)
![](https://velog.velcdn.com/images/bon0057/post/116040e4-013b-4db3-92b6-df78191640d3/image.png)
- 'b' 유저가 로그인한 상황에서 'a' 유저가 작성한 게시글과 댓글의 수정, 삭제 버튼이 비활성화 
![](https://velog.velcdn.com/images/bon0057/post/01701466-3539-4057-9a4d-db14d5b47db4/image.png)
- 'a' 유저가 로그인한 상황에서 본인이 작성한 게시글과 댓글의 수정, 삭제 버튼이 활성화

### 관리자 유저 추가
```java
@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;
}
```
- 멤버 엔티티에 들어갈 Role Enum 추가 

![](https://velog.velcdn.com/images/bon0057/post/1befa5bc-5007-4bf7-b7e5-990a479f4a57/image.png)

![](https://velog.velcdn.com/images/bon0057/post/f8161ba2-86c3-4bb0-8b15-eb5ab1fede79/image.png)
- 관리자 아이디로 로그인 시에 모든 게시글, 댓글 수정, 삭제 버튼 활성화 

### 내 정보에서 게시글, 댓글 삭제 기능 
![](https://velog.velcdn.com/images/bon0057/post/a96f9c04-c83b-40c3-bbab-bee1afe71f75/image.png)

### 게시판 분류 추가 
- 게시판을 공지사항 / 자유게시판 / 질문게시판으로 분류했다. 
 ![](https://velog.velcdn.com/images/bon0057/post/5049da3e-9f23-4a05-8374-4b04f0bbf0e8/image.png)

- 게시글에 들어가면 어떤 게시판인지 볼 수 있게 태그를 넣었다. 
![](https://velog.velcdn.com/images/bon0057/post/4f72efe6-b9f0-4a4e-b2f5-71315492b675/image.png)
- 검색 조건이 현재 제목, 게시판 분류 2개이기 때문에 조건이 더 추가된다면 Querydsl을 도입해야 코드 작성에 편할 것 같다. 

## 구현 - V4 (2024/02/08 ~ 
### 로그인 검증 & 오류 문구 추가
![](https://velog.velcdn.com/images/bon0057/post/31ecd76d-e428-40f2-8a8e-4875ffd5bc23/image.png)
- 아이디 또는 비밀번호가 틀려 로그인에 실패했을 경우 오류 메세지 출력 
![](https://velog.velcdn.com/images/bon0057/post/e6f31064-c775-41de-8e09-321ecbdf54d9/image.png)
- 아이디, 비밀번호에 빈칸을 허용하지 않는 정책 도입, 빈칸 입력 시 오류 메세지 출력 

### 게시글 작성 검증 추가 
![](https://velog.velcdn.com/images/bon0057/post/195731b7-3305-4362-91d2-1bfecfa8010e/image.png)
- 게시판 카테고리, 제목, 내용에 빈칸을 허용하지 않는 정책 도입, 빈칸 입력 시 오류 메세지 출력

### 댓글 작성 검증 추가
![](https://velog.velcdn.com/images/bon0057/post/589da200-2d85-4b89-af12-e9ee1a59b01f/image.png)
- 댓글 내용에 빈칸을 허용하지 않는 정책 도입, 빈칸 입력 시 오류 메세지 출력 


