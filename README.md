# Board V1 - Spring Boot 게시판 애플리케이션

Spring Boot와 MyBatis를 활용한 모던 게시판 애플리케이션입니다. REST API와 웹 인터페이스를 모두 제공합니다.

## 기술 스택

### Backend
- **Spring Boot 3.1.5**
  - Spring Web
  - Spring Transaction
  - Lombok
- **MyBatis**
  - MyBatis Spring Boot Starter
- **PostgreSQL**
  - PostgreSQL JDBC Driver

### Frontend
- **Thymeleaf**
  - Thymeleaf Layout Dialect
- **Bootstrap 5.3.0**
  - 반응형 디자인
  - 모던 UI 컴포넌트

## 아키텍처

### 계층형 아키텍처
```
Presentation Layer (Web/API) → Service Layer → Data Access Layer
        ↓                           ↓               ↓
    DTO/Form                     Domain          Mapper
```

### 패키지 구조
```
com.hakg.boardv1
├── domain          # 도메인 모델
├── mapper         # MyBatis 매퍼
├── service        # 비즈니스 로직
├── api            # REST API
│   ├── controller # API 컨트롤러
│   └── dto        # API 요청/응답 DTO
└── web            # 웹 인터페이스
    ├── controller # 웹 컨트롤러
    └── dto        # 웹 폼 DTO
```

## 디자인 패턴

- **DTO 패턴**: 계층 간 데이터 전송
- **레이어드 패턴**: 관심사 분리
- **MVC 패턴**: 웹 계층 구조화

## 주요 기능

### REST API
- `GET /api/boards`: 게시글 목록
- `GET /api/boards/{id}`: 게시글 조회
- `POST /api/boards`: 게시글 생성
- `PUT /api/boards/{id}`: 게시글 수정
- `DELETE /api/boards/{id}`: 게시글 삭제

### 웹 인터페이스
- 게시글 목록 (`/board`)
- 게시글 작성 (`/board/write`)
- 게시글 조회 (`/board/{id}`)
- 게시글 수정 (`/board/{id}/edit`)
- 게시글 삭제

## 시작하기

### 필수 조건
- Java 17
- PostgreSQL 데이터베이스
- Gradle

### 데이터베이스 설정
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/board_v1
    username: [사용자이름]
    password: [비밀번호]
```

### 빌드 및 실행
```bash
./gradlew build
java -jar build/libs/board-v1-0.0.1-SNAPSHOT.jar
```

## 특징

- **클린 아키텍처**: 계층형 구조로 관심사 분리
- **REST API**: HTTP 메서드를 활용한 RESTful 엔드포인트
- **반응형 UI**: Bootstrap을 활용한 모바일 친화적 디자인
- **템플릿 엔진**: Thymeleaf로 서버사이드 렌더링
- **데이터베이스**: MyBatis로 효율적인 데이터 접근

## 향후 계획

- [ ] 페이지네이션 구현
- [ ] 검색 기능 추가
- [ ] 사용자 인증
- [ ] 파일 업로드
- [ ] API 문서화 (Swagger)
