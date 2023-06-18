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

## 참고 강의
인프런 김영한님
