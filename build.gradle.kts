import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val mvnRepository: String = System.getenv("mvnRepository")
val mvnUser: String = System.getenv("mvnUser")
val mvnPassword: String = System.getenv("mvnPassword")

plugins {
    kotlin("jvm") version "1.6.0"
}

group = "com.theevilroot"
version = "1.0-1.3"

repositories {
    mavenCentral()
    maven(url = "https://oss.sonatype.org/content/groups/public/")
    maven {
        url = uri(mvnRepository)
        credentials {
            username = mvnUser
            password = mvnPassword
        }
    }
}

dependencies {
    implementation("me.theevilroot:minikeyvaluekt:1.3")
    implementation("me.theevilroot:minikeyvaluekt-gson:1.3")
    implementation("me.theevilroot:minikeyvaluekt-okhttp:1.3")
    implementation("com.github.jsqlparser:jsqlparser:4.4")
    testImplementation(kotlin("test"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}