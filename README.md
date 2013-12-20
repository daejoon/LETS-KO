#LET'S KO Project
##차례

```
1. Let's Ko Project
    1.1. Let's Ko Project에 대해서
    
2. 개발환경 구성 및 설치
    2.1. 개발환경 구성
    2.2. IntelliJ IDEA 13 설치
    2.3. Git 설치
    2.4. Maven 설치
    2.5. Web Application Server 설치
    2.6. DataBase 설치
    
3. 프로젝트 생성 및 설정
    3.1. Java Project 생성
    3.2. POM.xml 파일 생성
    3.3. WAS Run 설정
    
4. SpringFramework
    4.1. SpringFrameWork 설정
    
5. SpringSecurity
    5.1. SpringSecurity 설정
    
6. Hibernate
    6.1. Hibernate 설정

7. Tiles
    7.1. Tiles 설정

8. Frontend
    8.1. Bootstrap 설정
    8.2. requirejs 설정
    8.3. jQuery 설정
    8.4. jQueryUi 설정
    8.5. jqGrid 설정
    
9. jqGrid를 이용한 간단한 CRUD
    9.1. Table 생성
    9.2. Hibernate Entity 생성
    9.3. DAO, Service, Controller 생성
    9.4. View 생성
    
10. 마치며
    10.1 마치며
```

## Let's Ko Project
##### Let's Ko Project에 대해서
>실제 프로젝트 시작전까지 비지니스 로직를 제외한 개발환경 구축 및 간단한 CRUD Sample을 만들어 보는 것이다.

## 개발환경 구성 및 설치
##### 개발환경 구성
>IDE 개발툴은 개발자의 생산성과 편의성을 크게 증대 시킨다고 생각한다. 사실 이제 IDE을 쓰지 않고 자바 개발을 한다는
생각은 하기 힘들다. 자바 IDE는 크게 오픈소스인 Eclipse, NetBeans와 상용 IDE인 IntelliJ IDEA(이하 IntelliJ)가 있다.
이번 프로젝트는 IntelliJ를 사용했다. IntelliJ는 상용 IDE로써 다양한 프레임워크를 신속히 지원하고 많은 부분이 Bundle로
제공하고 있어 오픈소스인 Eclipse보다 설정이 적다. (그렇다고 설정을 안하고 쓸수 있다는 것은 아니다.) 플렛폼은 Windows, Linux, Mac을 지원한다.
여기서는 Windows를 선택했다. Windows로 간 이유중 하나는 자바 버전이다. 지금은 오라클에서 맥용도 지원을 하지만 예전 버전(1.6미만 버전)은 여전히 구하기 쉽지 않다.
그리고 SQLServer를 사용한것도 Windows쪽을 선택하게 한 이유이다.
```
개발환경
    |--- IDE: IntelliJ IDEA13
    |--- Java: 1.7
    |--- 형상관리: Git
    |--- 빌드: Maven
    |--- Web Application Server: Tomcat7.0
    |--- DB: SQLServer 2008
```
```
Server Side
    |--- SpringFramework
    |--- SpringSecurity
    |--- Hibernate
    |--- tiles
```
```
Frontend
    |--- jQuery
    |--- jQuery-ui
    |--- requirejs
    |--- jqgrid
    |--- bootstrap
```

##### IntelliJ IDEA13 설치
>IntelliJ IDEA13 Windows 용을 [다운로드](http://www.jetbrains.com/idea/download/index.html)한다.
>IntelliJ 설정은 [기본설정](http://beyondj2ee.wordpress.com/2013/06/01/%EC%9D%B8%ED%85%94%EB%A6%ACj-%EC%8B%9C%EC%9E%91%ED%95%98%EA%B8%B0-part1-getting-start-intellij-%EA%B8%B0%EB%B3%B8-%EC%84%A4%EC%A0%95%ED%8E%B8/), [자바설정](http://beyondj2ee.wordpress.com/2013/06/15/%EC%9D%B8%ED%85%94%EB%A6%ACj-%EC%8B%9C%EC%9E%91%ED%95%98%EA%B8%B0-part2-getting-start-intellij-%EC%9E%90%EB%B0%94-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%ED%8E%B8/), 을 참고한다.

##### Git 설치
>Git은 리눅스 커널을 개발 관리하기 위해서 [리누스 토발즈](http://ko.wikipedia.org/wiki/%EB%A6%AC%EB%88%84%EC%8A%A4_%ED%86%A0%EB%A5%B4%EB%B0%9C%EC%8A%A4)가 만든 형상관리 툴이다. 기본적으로 Linux계열에서 동작하며 Windows에서 사용하려면 [msysgit](https://code.google.com/p/msysgit/downloads/list?q=full+installer+official+git)를 사용하면 된다. 현재 최선 버전은 1.8.4가 최신 버전이다. msysgit설치 후 IntelliJ와 연동 설정을 한다.

>IntelliJ와 연동은 [IntelliJ-Git 설정](http://beyondj2ee.wordpress.com/2013/06/28/%ec%9d%b8%ed%85%94%eb%a6%acj-%ec%8b%9c%ec%9e%91%ed%95%98%ea%b8%b0-part4-getting-start-intellij-git/)을 참고한다.

##### Maven 설치
>의존성 관리만으로도 [Maven](http://maven.apache.org/download.cgi)을 설치할만한 가치는 충분이 크다. Maven 현재 최신 버전은 3.1.1 이다.
Maven 3.1.1 (Binary zip)을 다운로드 한후, 적당한 폴더에 압축해제한다.
"[IntelliJ Menu]->File->settings...->Maven->Maven Home directory->[선택]" 압축해제한 곳을 선택한다.

##### Web Application Server
>Web Application Server(이하 WAS)는 여러 밴더가 존재한다. 여러가지 현실적 이유때문에 개발시 개발환경과 실제 배포환경의 WAS가 일치하지 않는 경우가 있다.
사실 WAS는 표준 기준이 있기 때문에 각 밴더별로 Version만 맞춰주면 동일한 결과를 보장해야 한다. Tomcat 7.0 Version은 [여기를](http://tomcat.apache.org/whichversion.html) 참고한다.
개발은 일반적으로 많이 사용하는 Tomcat을 이용한다. [Tomcat 7.0 다운로드](http://tomcat.apache.org/download-70.cgi)

