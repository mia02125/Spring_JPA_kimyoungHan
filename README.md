# 실전! 스프링 부트와 JPA 활용1 - 웹 애플리케이션 개발(김영한) 
```
* 개발환경 : Spring Boot, Thymeleaf, Spring Data JPA, Lombok, H2 DataBase
```
### 1. 프로젝트 생성
<img src="https://raw.githubusercontent.com/mia02125/Spring_JPA_kimyoungHan/master/referIMG/setting/setting.png" width="400" height="300">
 
### 2. 환경 설정 
 - lombok까지 모두 설치 후 Annotation Processors에서 Enable annotation processing 체크박스 선택 
 <img src="https://raw.githubusercontent.com/mia02125/Spring_JPA_kimyoungHan/master/referIMG/setting/set_annotation_after_lombok.PNG" width="400" height="300">
 
### 3. Maven 설정 및 검증
#### 핵심 : (스프링(MVC, ORM, 데이터 JPA), DATA JPA, Hibernate 
#### 기타 : H2 DB, HikariCP(Connection Pool), Thymeleaf, loging(SLF4J, LogBack), TEST
 <img src="https://raw.githubusercontent.com/mia02125/Spring_JPA_kimyoungHan/master/referIMG/setting/dependencies.PNG" width="400" height="400">
 
```
중요!!!
 1) Spring_Boot_Starter_Web : tomcat과 webmvc과 의존성 
 2) Spring_Boot_Starter_Data_JPA : AOP와 JDBC과 의존성
    - jdbc : HikariCP (커넥션 풀) 
               a) boot 2.0.0부터 기본으로 제공 
           : Hibernate 제공 
```


### 4. 환경설정 및 간단 API 구현

```yml
spring:
  datasource:
    url: jdbc:h2:tcp://localhost/~/test
    username: user
    password : 1234
    driver-class-name: org.h2.Driver

  jpa:
    hibernate:
      ddl-auto: create # 자동 테이블 생성
    properties:
      hibernate:
        format_sql: true


logging:
  level:
    org.hibernate.SQL: debug # JPA가 생성하는 모든 SQL 로그가 보임
```

 <img src="https://raw.githubusercontent.com/mia02125/Spring_JPA_kimyoungHan/master/referIMG/restapi/createDelete.PNG" width="400" height="300">
 
```
JPA는 Transaction을 기반으로 작동되기 때문에 @Transactional 필수로 적용 
* 테스트 환경에서는 트랜젝션 후 Rollback이 default로 이루어짐
```

### 5. logging 설정 
#### p6spy

<img src="https://raw.githubusercontent.com/mia02125/Spring_JPA_kimyoungHan/master/referIMG/log/logSetting.PNG" width="400" height="300">

<img src="https://raw.githubusercontent.com/mia02125/Spring_JPA_kimyoungHan/master/referIMG/log/logConsole.PNG" width="400" height="300">

```
* 로깅 처리 
 - logging: 
      level: 
         org.hibernate.type : trace <= 요청 파라미터 출력 
 - 외부 라이브러리 https://github.com/gavlyukovskiy/spring-boot-data-source-decorator
   1) p6spy 적용 
   * 운영 환경 시 성능 테스트 필요 

```

### 6. Entity 관계 정의

<img src="https://raw.githubusercontent.com/mia02125/Spring_JPA_kimyoungHan/master/referIMG/entity/setting.PNG" width="400" height="300">

<img src="https://raw.githubusercontent.com/mia02125/Spring_JPA_kimyoungHan/master/referIMG/entity/relation.PNG" width="400" height="300">

```
연관관계의 주체는 FK에 가까운 Entity인 Order테이블을 기준으로
두기때문에 mappedBy 사용하여 order에 직접적인 영향을 주지않고 읽기 전용으로 한다.
ex) member정보를 update할 경우 order테이블이 update되는 경우가 생기는 오류 발생
( mappedBy = [Order테이블에 존재하는 Member의 변수명] )
```
