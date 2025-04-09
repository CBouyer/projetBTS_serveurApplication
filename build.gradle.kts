val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "2.1.0"
    id("io.ktor.plugin") version "3.0.2"
}

group = "com.moser"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-auth-jvm")
    implementation("io.ktor:ktor-server-auth-jwt-jvm")
    implementation ("org.mindrot:jbcrypt:0.4")
    implementation("com.auth0:java-jwt:4.4.0")
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-gson-jvm")
    implementation("io.ktor:ktor-server-call-logging-jvm")
    implementation("io.ktor:ktor-server-freemarker-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("com.mysql:mysql-connector-j:9.0.0")
    implementation("io.ktor:ktor-server-cors-jvm")
    implementation("com.hivemq:hivemq-mqtt-client:1.3.5")
    implementation ("com.google.code.gson:gson:2.12.1")
    implementation("io.ktor:ktor-server-websockets-jvm:2.2.4")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:2.3.7")

    testImplementation("io.ktor:ktor-server-test-host-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}
