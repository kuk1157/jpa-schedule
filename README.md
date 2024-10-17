# [ Spring 개인과제_2 - 일정관리 게시판 (JPA 기반)  ]

필수 레벨 0,1,2,3,4,5 : https://kuk1938.tistory.com/170

도전 레벨 1,2,3,4 : https://kuk1938.tistory.com/173

트러블 슈팅 : https://kuk1938.tistory.com/172

#

### 필수 레벨0 - 일정관리 설계서) 

![image](https://github.com/user-attachments/assets/c7015e1b-38cf-4a40-9886-655c8e830ede)


ㄴ ERD

#
![image](https://github.com/user-attachments/assets/9b32c03e-c57f-4d5f-9d1f-afa5a6c20de8)



ㄴ 요구사항 및 API명세서

#

![image](https://github.com/user-attachments/assets/17c5ac66-6379-414c-bc8b-873e7514ef54)

ㄴ API명세서 상세

https://moonddev-9794.postman.co/workspace/moonddev-Workspace~c3ed51a4-6c51-4a48-b8d6-b6e3245bdf3a/collection/38606783-4488f9b4-7486-4c21-9dd4-61428be01746?action=share&creator=38606783

ㄴ API명세서 링크



# [일정관리 전체 흐름]
: 필수,도전으로 분리하여 설명

## [필수 레벨1]

#### 일정 등록)

- 할일제목, 할일내용 입력
- @NotBlank, @Size를 활용하여 예외처리
- 유저 ID를 체크해서 유저가 존재할 경우만 일정 등록

#### 일정 조회)

- 전체 조회


#### 일정 수정) - 도전레벨3과 연동

- 할일제목, 할일내용 입력
- API경로 마지막에 일정ID를 넣어서 수정진행 (@PathVariable활용)
- 로그인한 토큰을 기준으로 회원의 권한을 조회하여 ADMIN일 경우에만 일정 수정 가능하도록 (@CookieValue)활용

#### 일정 삭제) - 도전레벨3과 연동

- API경로 마지막에 일정ID를 넣어서 수정진행 (@PathVariable활용)
- 로그인한 토큰을 기준으로 회원의 권한을 조회하여 ADMIN일 경우에만 일정 삭제 가능하도록 (@CookieValue)활용
- 일정 삭제시 댓글의 s_id 컬럼(외래키)과 연동되어 댓글도 함께 삭제


## [필수레벨 2]

#### 댓글 등록)

- 댓글 내용 입력
- @NotBlank, @Size를 활용하여 예외처리

#### 댓글 조회)

- 전체 조회

#### 댓글 수정) 

- 댓글 내용 입력
- API경로 마지막에 댓글ID를 넣어서 수정진행 (@PathVariable활용)

#### 댓글 삭제) - 도전레벨3과 연동

- API경로 마지막에 댓글ID를 넣어서 삭제진행 (@PathVariable활용)


## [필수레벨 3]

#### 일정 페이징)

- Pageable 인터페이스 활용
- page, size를 API전송시 각각 입력하여 진행 (페이지번호, 게시글 수)
- 항목 4가지 + 댓글 개수, 작성 유저명 서브쿼리로 불러오기, 수정일 기준 내림차순정렬


## [필수레벨 4]

#### 유저 등록(회원가입) - 도전 레벨1과 연동 )

- 이름,이메일,비밀번호 3가지 항목입력 및 - @NotBlank, @Size, @regexp를 활용하여 예외처리
- admintoken 유무로 관리자권한 부여

#### 유저 조회)

- 전체조회

#### 유저 수정)

- 이름, 이메일 입력
- API경로 마지막에 유저ID를 넣어서 수정진행 (@PathVariable활용)

#### 유저 삭제)

- API경로 마지막에 유저ID를 넣어서 삭제진행 (@PathVariable활용)

## [필수레벨 5]

#### 예외 처리)

- @NotBlank, @Size, @regexp 사용


## [도전레벨 1]

#### 회원가입 )

- PasswordEncoder활용하여 비밀번호 암호화
- admintoken 유무로 관리자권한 부여
- 유저 수정시 비밀번호는 변경되지 않도록


## [도전레벨 2]

#### 로그인(인증) )

- 이메일 비밀번호 입력하여 로그인 진행
- JWT발급 및 저장
- 예외처리 진행


## [도전레벨 3]

#### 권한확인(인가) )

- 회원가입시 등록한 유저권한 활용
- 일정,수정 삭제시 @CookieValue 활용해서 JWT체크
- 회원정보 중 권한가져와서 예외처리 진행 ADMIN일때만 일정 수정, 삭제가능하도록
  

## [도전레벨 4]

#### API 조회 )

- 날씨 API 조회
- 정보 가공하여 오늘 날짜를 기준으로 해당 날씨 추출
- 날씨를 일정등록할때 weather 컬럼에 담아서 일정등록시 정상적으로 weather값 같이 등록



## API조회)

https://github.com/user-attachments/assets/0a2a9f9a-8c3c-4ac7-b075-f9df562488c4
