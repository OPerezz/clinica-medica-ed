
plugins {
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit)
    implementation(libs.guava)


    implementation("org.kordamp.ikonli:ikonli-javafx:12.3.1")
    implementation("org.kordamp.ikonli:ikonli-fontawesome5-pack:12.3.1")
    implementation("com.mysql:mysql-connector-j:9.5.0")
    
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

javafx {
    version = "25"
    modules = listOf("javafx.controls", "javafx.fxml")
}

application {
    mainClass = "edu.curso.App"
}

