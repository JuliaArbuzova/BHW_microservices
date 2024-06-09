plugins {
	id("org.jetbrains.kotlin.jvm") version "1.6.10" apply false
	id("org.jetbrains.kotlin.plugin.spring") version "1.6.10" apply false
	id("org.springframework.boot") version "2.6.4" apply false
	id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
}

subprojects {
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")

	repositories {
		mavenCentral()
	}

	dependencies {
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
	}

	java {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}

	tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
		kotlinOptions {
			jvmTarget = "17"
		}
	}
}
