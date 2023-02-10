plugins {
    java
    id("org.springframework.boot") version "3.0.2"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "org.lhq"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    maven { url=uri("https://maven.aliyun.com/repository/public/") }
    maven { url=uri("https://maven.aliyun.com/repository/spring/") }
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-log4j2:3.0.2")
    implementation("com.baomidou:mybatis-plus-boot-starter:3.5.3.1")
    implementation ("cn.hutool:hutool-all:5.8.11")
    implementation("com.github.xiaoymin:knife4j-openapi3-jakarta-spring-boot-starter:4.0.0")
    implementation("com.lmax:disruptor:3.4.4")
    implementation("mysql:mysql-connector-java:8.0.30")
    implementation ("com.microsoft.graph:microsoft-graph:5.+")
    // Include Azure identity for authentication
    implementation ("com.azure:azure-identity:1.+")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

configurations.all() {
    exclude(group="org.springframework.boot",module = "spring-boot-starter-logging")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
