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
<div style="display: flex">
 <img src="https://raw.githubusercontent.com/mia02125/Spring_JPA_kimyoungHan/master/referIMG/entity/setting.PNG" width="400" height="300">
 <img src="https://raw.githubusercontent.com/mia02125/Spring_JPA_kimyoungHan/master/referIMG/entity/DBsetting.PNG" width="400" height="300">
</div>
<div style="display: flex">
 <img src="https://raw.githubusercontent.com/mia02125/Spring_JPA_kimyoungHan/master/referIMG/entity/relation.png" width="700" height="300">
 <img src="https://raw.githubusercontent.com/mia02125/Spring_JPA_kimyoungHan/master/referIMG/entity/relation1.png" width="700" height="300">
</div>
```html
oneToMany 연관관계의 주체는 FK에 가까운 Entity인 Order테이블을 기준으로
두기때문에 mappedBy 사용하여 order에 직접적인 영향을 주지않고 읽기 전용으로 정의
* 연관관계의 주체는 @JoinColumn 정의 
ex) member정보를 update할 경우 order테이블이 update되는 경우가 생기는 오류 발생
( mappedBy = [Order테이블에 존재하는 Member의 변수명] )

oneToOne 연관관계의 주체는 상황마다 다르지만 저자의 경우 Access하는 기준을 연관관계의 주체로 잡음
```

<div style="display: flex">
 <img src="https://raw.githubusercontent.com/mia02125/Spring_JPA_kimyoungHan/master/referIMG/entity/inheritance.PNG" width="400" height="300">
 <img src="https://raw.githubusercontent.com/mia02125/Spring_JPA_kimyoungHan/master/referIMG/entity/Discriminator.PNG" width="400" height="300">
</div>

```
@Inheritanceh(상속 매핑)
 1. SINGLE_TABLE : 테이블을 하나만 사용하는 방식(조인이 필요없기 때문에 조회 성능이 빠름)
 2. JOINED : 조인 전략(쿼리 복잡 및 CRUD할 경우 부모/자식 클래스 모두 저장하기 때문에 두번의 쿼리 실행)
 3. TABLE_PER_CLASS : 각각의 테이블을 만드는 방식 (추천하지않음 -> union을 사용하기 때문에 성능이 느림)
 * 출처 : https://hyeooona825.tistory.com/90
 
@DiscriminatorColumn(name = "dtype") 
 - 부모클래스에 구분 컬럼 지정 
 
@DiscriminatorValue("B")
 - Entity 저장 시 구분 컬럼에 입력할 값을 지정
   ex) 'B'라 지정하면 Entity 저장 시 부모클래스인 Book의 dtype에 B가 저장
```

<img src="https://raw.githubusercontent.com/mia02125/Spring_JPA_kimyoungHan/master/referIMG/entity/embed.PNG" width="400" height="300">

```
@Embeddable 
 - JPA Enity내 하나의 객체로 사용하고 싶다면 해당 어노테이션 사용

```

<img src="https://raw.githubusercontent.com/mia02125/Spring_JPA_kimyoungHan/master/referIMG/entity/enum.PNG" width="400" height="300">

```
@Enumerated
1. ORDINAL : 숫자로 정의 
   * 절대 사용 금지 ex) Enum 가운데 구분값을 추가하면 시스템 전체적인 문제 발생
2. STRING : 문자열로 정의
```
