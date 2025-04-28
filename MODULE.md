# 🔴 모듈 추가 방법

1. 폴더내에 project 생성한다.
2. src, build.gradle 파일을 제외한 모든 파일을 제거한다.
3. build.gradle 파일을 수정한다.

```
예시

// api
group = 'com.simol'
version = '1.0.0'

tasks.withType(org.springframework.boot.gradle.tasks.bundling.BootJar) {
    enabled = true
    archiveBaseName = "kong"
    archiveVersion = version
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation project(':simol-common')
    // aop
    implementation 'org.springframework.boot:spring-boot-starter-aop'
    // jwt
    implementation 'io.jsonwebtoken:jjwt-api:0.11.2'
    runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.2'
    runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.2'
    // security
    // implementation 'org.springframework.boot:spring-boot-starter-security'
    // jpa
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    // h2 데이터베이스 설정
    runtimeOnly 'com.h2database:h2'
    // mysql 데이터베이스 설정
    runtimeOnly 'com.mysql:mysql-connector-j'

    // actuator
    implementation 'org.springframework.boot:spring-boot-starter-actuator'
    implementation 'io.micrometer:micrometer-registry-prometheus'
}
```

4. 루트 build.gradle 파일을 수정한다.

```
예시 (추가만 해주면 된다.)

project(':kong') {

	dependencies {
		implementation project(':simol-common')
	}
}
```

5. setttings.gradle 파일에 추가한다.

```
예시

...

include 'kong'
```

6. 루트 build.gradle 에서 리로드 해준다.

7. 만약 실행이 안된다면 ./gradlew clean build 를 통해 새롭게 빌드 후 실행해보자.