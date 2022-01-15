import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.0"
	kotlin("plugin.spring") version "1.6.0"
	id("org.jetbrains.kotlin.plugin.noarg") version "1.6.0"
	id("org.jetbrains.kotlin.plugin.jpa") version "1.6.0"
}

group = "com.spacebox"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone")}
}

noArg {
	invokeInitializers = true
}

extra["springCloudVersion"] = "2021.0.0"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// feign
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
	implementation("io.github.openfeign:feign-gson:11.0")
	implementation("io.github.openfeign.form:feign-form-spring:3.8.0")

	// log4j
	implementation("org.apache.logging.log4j:log4j-api-kotlin:1.0.0")
	implementation("org.apache.logging.log4j:log4j-api:2.14.1")
	implementation("org.apache.logging.log4j:log4j-core:2.14.1")

	// liquibase + JPA
	implementation("org.liquibase:liquibase-core:4.6.1")
	implementation("org.postgresql:postgresql:42.3.1")


	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// Mockk
	testImplementation("io.mockk:mockk:1.12.0")

	// Strikt
	testImplementation("io.strikt:strikt-core:0.32.0")
	testImplementation("com.christophsturm:filepeek:0.1.3")

	testImplementation("org.testcontainers:testcontainers:1.16.2")
	testImplementation("org.testcontainers:junit-jupiter:1.16.2")
	testImplementation("org.testcontainers:postgresql:1.16.2")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
