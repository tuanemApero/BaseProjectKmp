import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.jetbrains.kotlin.compose)
    alias(libs.plugins.jetbrains.compose)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "composeApp"
            isStatic = true
            linkerOpts.add("-framework")
            linkerOpts.add("MessageUI")
            linkerOpts.add("lsqlite3")
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.datetime)

                // Koin
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.viewmodel)
                implementation(libs.koin.compose.viewmodel.navigation)

                //Compose
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)

                /*view_model*/
                implementation(libs.androidx.lifecycle.viewmodel)
                implementation(libs.androidx.lifecycle.runtime)

                /*navigation*/
                implementation(libs.androidx.navigation.compose)

                /*serlization*/
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinInject)

                implementation(libs.landscapist.coil)

                /*back handler*/
                implementation(libs.back.handler)

                /*shimmer*/
                implementation(libs.compose.shimmer)

                /*muti module*/
                implementation(projects.shared)

                /*firebase*/
                implementation(libs.firebase.config)
                implementation(libs.firebase.analytic)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.compose.ui.tooling.preview)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        
        iosMain {
            dependsOn(commonMain.get())
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(compose.runtime)
            }
        }
    }
}

android {
    defaultConfig {
        namespace = "com.apero.composeapp"
        compileSdk = 35
        minSdk = 24
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

val iosTargets = listOf(
    kotlin.targets.getByName("iosX64") as KotlinNativeTarget,
    kotlin.targets.getByName("iosArm64") as KotlinNativeTarget,
    kotlin.targets.getByName("iosSimulatorArm64") as KotlinNativeTarget
)

tasks.register("assembleXCFramework") {
    group = "build"

    doFirst {
        buildDir.resolve("xcode-frameworks").deleteRecursively()
    }

    dependsOn(
        iosTargets.map {
            it.binaries.getFramework("DEBUG").linkTask
        }
    )

    doLast {
        val outputDir = buildDir.resolve("xcode-frameworks")
        outputDir.mkdirs()

        val frameworks = iosTargets.map {
            it.binaries.getFramework("DEBUG")
        }

        val command = listOf(
            "xcodebuild",
            "-create-xcframework"
        ) + frameworks.flatMap {
            listOf("-framework", it.outputDirectory.resolve("${it.baseName}.framework").absolutePath)
        } + listOf(
            "-output", outputDir.resolve("composeApp.xcframework").absolutePath
        )

        println("➡️ Running: ${command.joinToString(" ")}")
        exec {
            commandLine(command)
        }
    }
}


