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

