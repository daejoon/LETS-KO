# LET'S KO Project
### 차례
- Let's Ko Project
 + Let's Ko Project에 대해서
- 개발환경 구성 및 설치
 + 개발환경 구성
 + IntelliJ IDEA 13 설치
 + Git 설치
 + Maven 설치
 + Web Application Server 설치
 + DataBase 설치
- 프로젝트 생성 및 설정
 + Java Project 생성
 + Run/Debug Configurations 설정
 + Git 설정
- Tomcat 설정
 + UTF-8 설정
 + Jndi 설정
- web.xml
 + web.xml 설정
- SpringFramework
 + SpringFrameWork 설정
- SpringSecurity
 + SpringSecurity 설정
- Hibernate
 + Hibernate 설정
- Tiles
 + Tiles 설정
- Frontend
 + Bootstrap 설정
 + requirejs 설정
 + jQuery 설정
 + jQueryUi 설정
 + jqGrid 설정
- jqGrid를 이용한 간단한 CRUD
 + Table 생성
 + Hibernate Entity 생성
 + DAO, Service, Controller 생성
 + View 생성
- 마치며
 + 마치며

### Let's Ko Project
##### Let's Ko Project에 대해서
>실제 프로젝트 시작전까지 비지니스 로직를 제외한 개발환경 구축 및 간단한 CRUD Sample을 만들어 보는 것이다.

### 개발환경 구성 및 설치
##### 개발환경 구성
>IDE 개발툴은 개발자의 생산성과 편의성을 크게 증대 시킨다고 생각한다. 사실 이제 IDE을 쓰지 않고 자바 개발을 한다는
>생각은 하기 힘들다. 자바 IDE는 크게 오픈소스인 Eclipse, NetBeans와 상용 IDE인 IntelliJ IDEA(이하 IntelliJ)가 있다.
>이번 프로젝트는 IntelliJ를 사용했다. IntelliJ는 상용 IDE로써 다양한 프레임워크를 신속히 지원하고 많은 부분이 Bundle로
>제공하고 있어 오픈소스인 Eclipse보다 설정이 적다. (그렇다고 설정을 안하고 쓸수 있다는 것은 아니다.) 플렛폼은 Windows, Linux, Mac을 지원한다.
>여기서는 Windows를 선택했다. Windows로 간 이유중 하나는 자바 버전이다. 지금은 오라클에서 맥용도 지원을 하지만 예전 버전(1.6미만 버전)은 여전히 구하기
>쉽지 않다. 그리고 SQLServer를 사용한것도 Windows쪽을 선택하게 한 이유이다.
>
```
개발환경
    ├─ IDE: IntelliJ IDEA13
    ├─ Java: 1.7
    ├─ 형상관리: Git
    ├─ 빌드: Maven
    ├─ Web Application Server: Tomcat7.0
    └─ DB: SQLServer 2008
```
```
Server Side 기술 스택
    ├─ SpringFramework
    ├─ SpringSecurity
    ├─ Hibernate
    └─ tiles
```
```
Frontend 기술 스택
    ├─ jQuery
    ├─ jQuery-ui
    ├─ requirejs
    ├─ jqgrid
    └─ bootstrap
```
>
>설치에 앞서서 개인적으로 프로젝트와 관련된 JDK, IDE, Maven, Web Application Server등은 아래와 같이 한곳에 모아둔다.
```
C:\JavaDE
    ├─ java
    │   ├─ jdk1.6.0_45
    │   ├─ jdk1.7.0_45
    │   └─ jdk1.7.0_45(x64)
    ├─ JetBrains
    │   └─ IntelliJ IDEA 13.0
    ├─ maven
    │   └─ apache-maven-3.1.1
    └─ tomcat
        ├─ apache-tomcat-6.0.37
        └─ apache-tomcat-7.0.42
```

##### IntelliJ IDEA13 설치
>IntelliJ IDEA13 Windows 용을 [다운로드](http://www.jetbrains.com/idea/download/index.html)한다.
>IntelliJ 설정은 [기본설정](http://beyondj2ee.wordpress.com/2013/06/01/%EC%9D%B8%ED%85%94%EB%A6%ACj-%EC%8B%9C%EC%9E%91%ED%95%98%EA%B8%B0-part1-getting-start-intellij-%EA%B8%B0%EB%B3%B8-%EC%84%A4%EC%A0%95%ED%8E%B8/),
>[자바설정](http://beyondj2ee.wordpress.com/2013/06/15/%EC%9D%B8%ED%85%94%EB%A6%ACj-%EC%8B%9C%EC%9E%91%ED%95%98%EA%B8%B0-part2-getting-start-intellij-%EC%9E%90%EB%B0%94-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%ED%8E%B8/)을 참고한다.

##### Git 설치
>Git은 리눅스 커널을 개발 관리하기 위해서 [리누스 토발즈](http://ko.wikipedia.org/wiki/%EB%A6%AC%EB%88%84%EC%8A%A4_%ED%86%A0%EB%A5%B4%EB%B0%9C%EC%8A%A4)가 만든 형상관리 툴이다.
>기본적으로 Linux계열에서 동작하며 Windows에서 사용하려면 [msysgit](https://code.google.com/p/msysgit/downloads/list?q=full+installer+official+git)를 사용하면 된다.
>현재 최선 버전은 1.8.4가 최신 버전이다. msysgit설치 후 IntelliJ와 연동 설정을 한다.
>
>IntelliJ와 연동은 [IntelliJ-Git 설정](http://beyondj2ee.wordpress.com/2013/06/28/%ec%9d%b8%ed%85%94%eb%a6%acj-%ec%8b%9c%ec%9e%91%ed%95%98%ea%b8%b0-part4-getting-start-intellij-git/)을 참고한다.

##### Maven 설치
>의존성 관리만으로도 Maven을 설치할만한 가치는 충분이 크다. Maven 현재 최신 버전은 3.1.1 이다.
>Maven 3.1.1 (Binary zip)을 [다운로드](http://maven.apache.org/download.cgi) 한후, 적당한 폴더에 압축해제한다.
>"[IntelliJ Menu]->File->settings...->Maven->Maven Home directory->[선택]" 압축해제한 곳을 선택한다.

##### Web Application Server
>Web Application Server(이하 WAS)는 여러 밴더가 존재한다. 여러가지 현실적 이유때문에 개발환경과 배포환경의 WAS가 일치하지 않는 경우가 있다.
>사실 WAS는 표준 스펙이 존재하기 때문에 밴더별로 스펙만 맞춰주면 동일한 결과를 보장해야 한다.
>Tomcat 7.0 Version은 [여기를](http://tomcat.apache.org/whichversion.html) 참고한다.
>개발은 일반적으로 많이 사용하는 Tomcat을 이용한다. [Tomcat 7.0 다운로드](http://tomcat.apache.org/download-70.cgi)

##### Database 설치
>이 프로젝트에서는 Hibernate를 사용하기 때문에 사실 특정 DataBase를 설치할 필요는 없다. 취양에 맞춰 설치하자.

### 프로젝트 생성 및 설정
##### Java Project 생성
>이번 프로젝트는 새로운 프로젝트를 생성하기 보다는 GitHub에서 Checkout해서 생성한다. 처음 IntelliJ를 실행하면은 Dashboard가 나타난다.
>"Check out from version control->['GitHub' 선택]"한다.
>
>다음 단계로 Clone Repository 다이알로그가 나타나는데
>"Git Repository URL->['https://github.com/daejoon/LETS-KO.git' 입력]->
>Parent Directory->['C:\Users\\{계정이름}\IdeaProjects' 입력]->
>Directory Name->['LET-KO' 입력]->['Clone' 버튼 클릭]"한다.
>
>Import Project 다이알로그 창이 나타나면 "Create project from existing sources->['Next' 버튼 클릭]->['Next' 버튼 클릭]->
>['Unmark All' 버튼 클릭]->['Finish' 버튼 클릭]" 한다.
>
>"File->Import Module->['First.iml' 선택]->['OK' 버튼 클릭]" 한다.

##### Run/Debug Configurations 설정
> "Run->['Edit Configurations' 선택]" 한다.
> Run/Debug Configurations 다이알로그가 나타난다.
> "+->Tomcat Server->['Local' 클릭]" 한다.
> "Name->['First - Tomcat 7.0' 입력]->Application Server->['Tomcat 7.0' 선택]" 한다.
> "Fix->['First:war exploded' 선택]->['OK' 버튼 클릭]" 한다.
>
> "File->Project Structure...->Project Settings->Modules->['First' 모듈 선택]->Dependencies
> ->['+' 버튼 클릭]->Library...->Application Server Libraries->['Tomcat 7.0' 선택]
> ->['Add Selected' 버튼 클릭]" 하여 WAS에 의존적인 라이브러리를 링크한다.

##### Git 설정
>"File->Settings...->Version Control->Ignored Files->['+' 버튼 클릭]->Ignore all files under->['...' 선택]->['.idea' 폴더 선택]->['Ok' 버튼 클릭]"


### Tomcat 설정
##### UTF-8 설정
> "First->WEB-INF->web.xml"의 Encoding Filter를 UTF-8로 설정한다.
```
    <!-- Encoding Filter -->
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```
> "['Tomcat 7.0' Home 폴더 이동]->conf->server.xml" useBodyEncodingForURI="true", URIEncoding="UTF-8"을 추가한다.
```
    <Connector port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443"
               useBodyEncodingForURI="true"
               URIEncoding="UTF-8" />
```

##### Jndi 설정
> "['Tomcat 7.0' Home 폴더 이동]->conf->context.xml"에 Resource 엘리먼트를 추가한다.
```
	<Resource	name="{jndi이름: 예)jdbc/lets_ko_local}"
				auth="Container"
				type="javax.sql.DataSource"
				username="{DB 로그인 아이디}"
				password="{DB 로그인 비밀번호}"
				driverClassName="{DB 드라이버클래스네임: 예)com.microsoft.sqlserver.jdbc.SQLServerDriver}"
				url="{DB Url: 예)jdbc:sqlserver://localhost:1433;databaseName=lets_ko;integratedSecurity=false;}"
				maxActive="10"
				maxIdle="5"/>
```
> "First->WEB-INF->web.xml"의 Jndi를 설정한다.
```
    <!-- resource jndi -->
    <resource-ref>
        <description>DB Connection</description>
        <res-ref-name>jdbc/let_ko_local</res-ref-name>
        <res-type>javax.sql.DataSource</res-type>
        <res-auth>Container</res-auth>
    </resource-ref>
```

### web.xml
##### web.xml 설정
> "First->WEB-INF->web.xml"을 참고한다.

### SpringFramework
##### SpringFrameWork 설정
> SpringFramework(이하 Spring) 설정은 Root Context 설정과, Servlet Context 설정으로 나뉜다.
> Root Context 설정은 Spring 전반적인 설정이고, Servlet Context 설정은 웹과 관련된 설정이다. Root Context 설정은
> Servlet Context로 상속된다.
> Servlet Context 설정은 "First->web->WEB-INF->config->springmvc->servlet-*.xml"을 참고한다.
> Root Context 설정은 "First->src->main->resources->config->spring->context-*.xml"을 참고한다.

### SpringSecurity
##### SpringSecurity 설정

### Hibernate
##### Hibernate 설정

### Tiles
##### Tiles 설정

### Frontend
##### Bootstrap 설정
##### requirejs 설정
##### jQuery 설정
##### jQueryUi 설정
##### jqGrid 설정

### jqGrid를 이용한 간단한 CRUD
##### Table 생성
##### Hibernate Entity 생성
##### DAO, Service, Controller 생성
##### View 생성

### 마치며
##### 마치며

