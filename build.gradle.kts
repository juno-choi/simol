plugins {
	java
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
	jacoco
}

group = "com.simol"
version = "1.0"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
	implementation("org.springdoc:springdoc-openapi-starter-webflux-api:2.5.0")
	compileOnly("org.projectlombok:lombok")
//	developmentOnly("org.springframework.boot:spring-boot-docker-compose")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("com.mysql:mysql-connector-j")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	// ✅ querydsl을 설치합니다. ":jakarta"를 꼭 설정합니다
	implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
	annotationProcessor("com.querydsl:querydsl-apt:5.0.0:jakarta")
	// java.lang.NoClassDefFoundError(jakarta.persistence.Entity) 발생 대응
	annotationProcessor("jakarta.persistence:jakarta.persistence-api")
	implementation("org.springframework:spring-aop:6.0.11")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
jacoco {
	toolVersion = "0.8.12"
}
tasks.jacocoTestReport {
	reports {
		html.required = true
		xml.required = false
		csv.required = false
		html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
	}
}

tasks.jacocoTestCoverageVerification {
	violationRules {
		rule {
			enabled = true
			element = "CLASS"

			// 라인 커버리지를 최소한 80%
			limit {
				counter = "LINE"
				value = "COVEREDRATIO"
				minimum = "1.00".toBigDecimal()
			}

			// 브랜치 커버리지를 최소한 90%
			limit {
				counter = "BRANCH"
				value = "COVEREDRATIO"
				minimum = "1.00".toBigDecimal()
			}

			// 빈 줄을 제외한 코드의 라인수를 최대 200라인으로 제한합니다.
			limit {
				counter = "LINE"
				value = "TOTALCOUNT"
				maximum = "200".toBigDecimal()
			}

			excludes = listOf(
				"*.ApplingApplication*"
				, "*.global.*"
				, "*.Q*"
			)
		}
	}
}

tasks.check {
	dependsOn(tasks.jacocoTestCoverageVerification) // `check` 태스크가 실행될 때 커버리지 검증을 포함하도록 설정
}

tasks.getByName<Test>("test") {
	extensions.configure(JacocoTaskExtension::class) {
		isEnabled = true
		includes = listOf()
		excludes = listOf()
		excludeClassLoaders = listOf()
		isIncludeNoLocationClasses = false
		sessionId = "<auto-generated value>"
		isDumpOnExit = true
		classDumpDir = null
		output = JacocoTaskExtension.Output.FILE
		address = "localhost"
		port = 6300
		isJmx = false
	}
}