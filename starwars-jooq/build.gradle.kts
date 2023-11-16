import org.jooq.meta.jaxb.ForcedType
import org.jooq.meta.jaxb.Logging
import org.jooq.meta.jaxb.Property

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.9.20"
    id("io.ktor.plugin") version "2.3.6"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.20"
    id("org.flywaydb.flyway") version "9.7.0"
    id("nu.studer.jooq") version "8.2.1"
}

group = "javajedi"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_19
}

configurations {
    create("flywayMigration")
}

flyway {
    url = "jdbc:postgresql://localhost:5432/starwars"
    user = "baggio"
    password = "baggio"
}

application {
    mainClass.set("javajedi.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")

    // Database
    // https://mvnrepository.com/artifact/org.flywaydb/flyway-core
    implementation("org.flywaydb:flyway-core:9.16.3")


    jooqGenerator("org.postgresql:postgresql:42.6.0")

    // HCP
    implementation("com.zaxxer:HikariCP:5.1.0")
    runtimeOnly("org.postgresql:postgresql:42.6.0")
    implementation("org.postgresql:r2dbc-postgresql:1.0.2.RELEASE")
    // Jooq and Kotlin coroutines
    implementation("org.jooq:jooq-kotlin:3.18.7")
    implementation("org.jooq:jooq-kotlin-coroutines:3.18.7")

    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

jooq {
    configurations {
        create("main") {
            jooqConfiguration.apply {
                logging = Logging.WARN
                jdbc.apply {
                    driver = "org.postgresql.Driver"
                    url = flyway.url
                    user = flyway.user
                    password = flyway.password
                    properties = listOf(
                        Property().apply {
                            key = "PAGE_SIZE"
                            value = "2048"
                        }
                    )
                }
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    database.apply {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        forcedTypes = listOf(
                            ForcedType().apply {
                                name = "varchar"
                                includeExpression = ".*"
                                includeTypes = "JSONB?"
                            },
                            ForcedType().apply {
                                name = "varchar"
                                includeExpression = ".*"
                                includeTypes = "INET"
                            }
                        )
                    }
                    generate.apply {
                        isDeprecated = false
                        isRecords = false
                        isImmutablePojos = false
                        isFluentSetters = false
                    }
                    target.apply {
                        packageName = "com.javajedi"
                        directory = "build/src/generated/jooq"
                    }
//                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                }
            }
        }
    }
}

val jooqTask = tasks.named("generateJooq").get()
val flywayTask = tasks.find { it.name == "flywayMigrate" }

jooqTask.dependsOn(flywayTask)
jooqTask.inputs.files(fileTree("src/main/resources/db/migration"))
    .withPropertyName("migrations")
    .withPathSensitivity(PathSensitivity.RELATIVE)
