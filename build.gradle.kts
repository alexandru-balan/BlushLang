plugins {
    java
    application
    kotlin("jvm") version "1.3.72"
}

group = "alexandru.balan.blush.lang"
version = "2020.alpha.3"

application {
    mainClassName = "blushlang.compiler.BlushCKt"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }
}