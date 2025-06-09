import com.codingfeline.buildkonfig.gradle.TargetConfigDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.buildConfig)
    alias(libs.plugins.kotlinx.serialization)
}

fun TargetConfigDsl.stringBuildConfig(name: String, value: String) {
    buildConfigField(
        type = com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
        name = name,
        value = value,
        const = true
    )
}

fun TargetConfigDsl.loadKey(name: String, path: String) {
    val publicKey = File("${rootDir}/$path").readText().trim()
        .replace(Regex("-----BEGIN PUBLIC KEY-----|-----END PUBLIC KEY-----|\n"), "").trim()

    stringBuildConfig(name, publicKey)
}

kotlin {
    androidLibrary {
        namespace = "com.apero.aiservicekmp"
        compileSdk = 35
        minSdk = 24

        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }
    val xcfName = "aiservicekmpKit"

    iosX64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)

                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)

                implementation(libs.koin.core)

                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.datetime)

                implementation(libs.filekit.core)
                implementation(libs.filekit.dialogs.compose)

            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.ktor.client.android)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.ktor.client.okhttp)
            }
        }

        getByName("androidDeviceTest") {
            dependencies {
                implementation(libs.androidx.runner)
                implementation(libs.androidx.core)
                implementation(libs.androidx.junit)
            }
        }

        iosMain {
            dependencies {
                implementation(libs.ktor.client.darwin)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.sqldelight.native.driver)
            }
        }
    }
}

buildConfig {
    buildConfigField("String", "BASE_URL_STYLE", "\"https://api-style-manager.apero.vn/\"")
    buildConfigField("String", "BASE_URL_GENERATE", "\"https://video-gen-core.apero.vn/\"")
    buildConfigField("String", "AIP_KEY_SUB", "\"appl_eSryaikTiJuleEYxFkrjqoMurcd\"")
    buildConfigField("String", "AIP_KEY", "\"appl_eSryaikTiJuleEYxFkrjqoMurcd\"")
    buildConfigField("String", "APPLICATION_ID", "\"com.aiartvideo.imageai.aigenerator\"")
    buildConfigField("String", "VERSION_APP", "\"1.0.0\"")
}