import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.5.4"
    id("io.spring.dependency-management") version "1.1.5"
    id("org.graalvm.buildtools.native") version "0.10.6"
    kotlin("jvm") version "1.9.24" // A versão do Kotlin deve ser compatível com a do Spring Boot
    kotlin("plugin.spring") version "1.9.24" // O plugin Spring para Kotlin
}

group = "br.com.afb"
version = "0.0.1-SNAPSHOT"
description = "Projeto de planejamento financeiro familiar"

java {
    toolchain {
        // Assume Java 21, que é a versão LTS recomendada
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
//    implementation("org.flywaydb:flyway-core:9.19.0")
    implementation("mysql:mysql-connector-java:8.0.33")

    // Dependências de teste
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        // Garante que o Kotlin compile para o Java 21
        jvmTarget = "21"
    }
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootBuildImage>("bootBuildImage") {
    // Define um nome e tag customizados para a sua imagem Docker
    imageName = "family-financial-planning:${project.version}"
}

tasks.withType<Test> {
    useJUnitPlatform()
}



