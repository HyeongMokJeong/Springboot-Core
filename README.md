# Springboot-Core

스프링부트 - 핵심 원리와 활용 실습 코드입니다.  

## Spring Framework의 등장
- EJB(Enterprise Java Beans)  
너무 복잡한 구조, 높은 인터페이스 의존성  
-> EJB 컨테이너를 대체하기 위해 Spring Framework 등장
   
1. 핵심 기술 : DI 컨테이너, AOP
2. 웹 기술 : 스프링 MVC
3. DB 접근 기술 : 트랜잭션, JDBC
4. 기술 통합 : 캐시, 스케줄링
5. 테스트 : 스프링 기반 테스트 지원

## SpringBoot의 등장
Spring Framework의 복잡한 초기 설정, 빈 등록 문제를 해결하기 위해 등장한 도구  

1. 내장 톰캣
2. 라이브러리 관리  
   2_1. 자동 버전 관리 
   2_2. 스타터 제공
3. 자동 구성(빈 자동 등록)
4. 외부설정과 프로필
5. 프로덕션 준비

## 내장 톰캣
<img src="https://github.com/HBNU-SWUNIV/come-capstone23-kjj/assets/94634916/2d82f0c4-9f6a-433f-884e-f5bf8cb6c11a">   

외장 서버 : 과거에는 서버에 WAS를 설치한 후, 서블릿 스펙에 맞추어 WAR(Web Application Archive) 형식으로 빌드하여 배포  
내장 서버 : SpringBoot 내부에 WAS(톰캣) 라이브러리가 포함되어 Jar(Java Archive)로 빌드한 후, Jar를 실행하기만 하면 동작  

- 하지만 Jar 파일은 내부에 Jar 파일을 포함할 수 없다.
  - 해결방안 : FatJar - jar를 풀어서 class들을 포함시키는 것
    1. 어떤 라이브러리가 포함되어 있는지 확인, 추적하기 어렵다.  
    2. 파일명 중복을 해결할 수 없다.
- 실행가능 Jar : SpringBoot에서 새롭게 정의한 특별한 구조의 Jar / 내부에 Jar를 포함한다.
1. META-INF/MANIFEST.MF 파일의 Main-Class에서 JarLauncher라는 클래스를 실행한다.
2. JarLauncher에서 내부 Jar를 읽어들인다.
3. 이후 JarLauncher가 MANIFEST.MF 파일의 Start-Class에 정의된 프로젝트 실행 메소드를 실행한다.

## 라이브러리 관리
- 자동 버전 관리  
"io.spring.dependency-management" 플러그인을 사용한다.  
버전 정보가 담긴 bom 문서를 참고하여 자동으로 버전을 구성한다.

- 스타터 제공
"spring-boot-starter-*" 형식의 스타터를 제공한다.  
스타터는 해당 테마에 필요한 관련 라이브러리를 포함한다.

## 자동 구성
- 자동 구성(Auto Configuration)  
일반적으로 자주 사용하는 수 많은 빈들을 자동으로 등록해주는 기능
라이브러리를 사용하는 개발자 입장에서, 라이브러리 내부의 빈을 등록하는 작업을 자동으로 처리해준다.
spring-boot-starter 내부의 spring-boot-autoconfigure에 의해 동작한다.

- @Conditional(Spring), @ConditionalOn*(SpringBoot)  
같은 소스코드에서 특정 상황일때만 기능이 동작하도록 사용하도록 도와주는 애노테이션  
자동 구성에서 주로 사용된다.  

- @AutoConfiguration
자동 구성 기능을 적용할 때 사용하는 애노테이션  
src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports의 정보를 읽어서 자동 구성으로 사용한다.  
@SpringBootApplication -> @EnableAutoConfiguration -> @Import(AutoConfigurationImportSelector.class)


## 외부설정과 프로필
외부설정: 환경에 따라 달라지는 설정값(DB URL 등)  
- OS 환경변수 : OS에서 지원하는 외부 설정
- 자바 시스템 속성 : JVM 안에서 사용하는 외부 설정(java -D{key}={value} -jar app.jar)
- 커맨드 라인 인수 : 애플리케이션 실행시점에 외부 설정값을 main(args)의 args 파라미터로 전달하는 방식(java -jar app.jar dataA dataB)
- 커맨드 라인 옵션 인수 : 스프링에서 편리함을 위해 제공하는 기능(java -jar app.jar --{key}={value})
- 외부 파일 : 프로그램 외부에서 파일을 직접 읽어서 이용(application.properties)  
<img src="https://github.com/HBNU-SWUNIV/come-capstone23-kjj/assets/94634916/21228561-2266-4d7d-a55b-1e5bb0b4e079">   

스프링 통합
- 외부 설정값이 어디에 있든 일관성있고 편리하게 조회할 수 있도록, 스프링은 Environment와 PropertySource라는 추상화를 통해서 해결했다.
- 스프링은 로딩 시점에 필요한 PropertySource들을 생성하고, Environment에서 사용할 수 있게 연결해 둔다.
- 같은 값이 존재할 경우, 1. 더 유연한 것, 2. 범위가 좁은 것이 우선권을 가진다.

외부 파일
- 내부 파일 분리  
  프로젝트 안에 설정 데이터를 포함하여 관리한다.(application-{profile}.properties)  
  실행할 때 환경을 구분하여 설정 데이터를 읽는다.(spring.profiles.active={profile})
- 내부 파일 합체
  하나의 파일에서 논리적으로 영역을 구분한다(#--- or !---, spring.config.activate.on-profile={profile})  

외부설정 사용
- Environment
- @Value
- @ConfigurationProperties
  외부 설정의 묶음 정보를 객체로 변환하는 기능(타입 안전한 설정 속성)

@Profile("{profile}")
- 해당 프로필이 활성화 된 경우에만 동작한다.
- 프로필 별로 빈을 구분할 수 있다.
- 내부적으로 @Conditional을 사용한다.

## 참고 강의
인프런 김영한님
