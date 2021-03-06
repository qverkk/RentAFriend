import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.1.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("jvm") version "1.3.50"
	kotlin("plugin.spring") version "1.3.50"
	kotlin("plugin.jpa") version "1.3.50"
}

group = "com.qverkk"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework:spring-context")
	implementation("org.springframework:spring-aop")
	implementation("com.squareup.okhttp3:okhttp:3.2.0")

//	implementation("com.google.auth:google-auth-library-oauth2-http:0.19.0")
	implementation("com.google.api-client:google-api-client:1.30.5")

	testImplementation("io.rest-assured:rest-assured:4.1.2")
	testImplementation("io.rest-assured:json-path:4.1.2")
	testImplementation("io.rest-assured:json-schema-validator:4.1.2")
	testImplementation("io.rest-assured:kotlin-extensions:4.1.2")
	testImplementation("io.rest-assured:xml-path:4.1.2")
	testImplementation("org.assertj:assertj-core:3.11.1")

	runtimeOnly("mysql:mysql-connector-java")
	testCompile("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.register("buildJarPublishDocker") {
	group = "build"
	description = "Creates a jar file and updates the docker image"

	dependsOn(tasks.clean)
	mustRunAfter(tasks.clean).dependsOn(tasks.bootJar)
	doLast {
		println("Done registering")
	}
}
