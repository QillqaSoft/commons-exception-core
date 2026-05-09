plugins {
    id("java-library")
    id("maven-publish")
}

group = "pe.quillqasoft.dev"
version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            pom {
                name.set("Commons Exception Core")
                description.set("This Java library simplifies exception handling by eliminating boilerplate code and automatically binding clean exceptions to centralized error catalogs via Convention over Configuration.")
            }
        }
    }
}