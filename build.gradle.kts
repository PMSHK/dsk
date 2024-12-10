plugins {
    java
    application
    id("org.javamodularity.moduleplugin") version "1.8.12"
    id("org.openjfx.javafxplugin") version "0.0.13"
    id("org.beryx.jlink") version "2.25.0"
}

tasks.named<Copy>("processResources") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.withType<Copy> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

sourceSets {
    main {
        resources {
            srcDirs("src/main/resources")
        }
    }
}

group = "com.xrc"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

application {
    mainModule.set("com.xrc.dsk")
    mainClass.set("com.xrc.dsk.DskApplication")
}

javafx {
    version = "17.0.6"
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.web", "javafx.swing")
}

dependencies {
    // JavaFX libraries
    implementation("org.controlsfx:controlsfx:11.1.2")
    implementation("com.dlsc.formsfx:formsfx-core:11.6.0") {
        exclude(group = "org.openjfx")
    }
    implementation("net.synedra:validatorfx:0.4.0") {
        exclude(group = "org.openjfx")
    }
    implementation("org.kordamp.ikonli:ikonli-javafx:12.3.1")
    implementation("org.kordamp.bootstrapfx:bootstrapfx-core:0.4.0")
    implementation("eu.hansolo:tilesfx:11.48") {
        exclude(group = "org.openjfx")
    }
    implementation("de.jensd:fontawesomefx-fontawesome:4.7.0-9.1.2")

    // Converters and utilities
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.0")
    implementation("org.slf4j:slf4j-api:2.0.16")
    implementation("org.projectlombok:lombok:1.18.28")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
    annotationProcessor("org.projectlombok:lombok:1.18.28")
    implementation("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    implementation("ch.qos.logback:logback-classic:1.5.12")

    // Test dependencies
    implementation(platform("org.testcontainers:testcontainers-bom:1.20.3"))
//    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("ch.qos.logback:logback-classic:1.5.12")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")
    testImplementation("org.assertj:assertj-core:3.26.3")
    testImplementation("org.junit.platform:junit-platform-launcher")
//    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks {
    test {
        useJUnitPlatform()
        testLogging {
            showStandardStreams = true
        }
    }

}

jlink {
    imageZip=layout.buildDirectory.file("distributions/app-${javafx.platform.classifier}.zip")
    options=listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages")
    launcher {
        name="app"
    }
}

tasks.register<Zip>("customJlinkZip") {
    group = "distribution"
    from(jlink.imageDir)
    destinationDirectory=layout.buildDirectory.dir("distributions")
    archiveFileName="app-${javafx.platform.classifier}.zip"
}