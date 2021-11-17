object BuildPlugins {
    object Versions {
        const val gradle = "7.0.2"
        const val kotlin = "1.5.31"
        const val hilt = "2.39.1"
        const val detekt = "1.18.1"
    }

    const val androidGradleDependency = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradleDependency =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val hiltGradleDependency = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val detektGradleDependency =
        "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.detekt}"
    const val safeArgsGradleDependency =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Libraries.Versions.navigation}"


}

object AndroidSdk {
    const val minSdk = 23
    const val compileSdk = 30
    const val targetSdk = 30
}

object AndroidApplication {
    const val appId = "com.medhdj.oplay"
    const val versionCode = 1
    const val versionName = "1.0"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Libraries {
    internal object Versions {
        const val kotlinKtx = "1.6.0"
        const val coroutines = "1.5.2"

        const val hilt = BuildPlugins.Versions.hilt

        const val retrofit = "2.9.0"
        const val okhttp = "4.7.2"
        const val gson = "2.8.9"

        const val appCompat = "1.3.1"
        const val materialDesign = "1.4.0"
        const val constraintLayout = "2.1.1"
        const val navigation = "2.3.5"
        const val lifeCycle = "2.3.1"
        const val fragmentKtx = "1.3.6"
        const val paging = "3.0.1"
        const val glide = "4.12.0"

        const val exoplayer = "2.16.0"

        const val jodaTime = "2.10.13"
        const val timber = "4.7.1"
    }

    // Kotlin
    const val kotlinCoreKtx = "androidx.core:core-ktx:${Versions.kotlinKtx}"

    // coroutines
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // DI
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"

    // Network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    // UI
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
    const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycle}"
    const val lifeCycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifeCycle}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    const val paging = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
    const val pagingCommon = "androidx.paging:paging-common:${Versions.paging}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    // Player
    const val exoplayerCore = "com.google.android.exoplayer:exoplayer-core:${Versions.exoplayer}"
    const val exoplayerUI = "com.google.android.exoplayer:exoplayer-ui:${Versions.exoplayer}"
    const val exoplayerDash = "com.google.android.exoplayer:exoplayer-dash:${Versions.exoplayer}"

    // utils
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val jodaTime = "joda-time:joda-time:${Versions.jodaTime}"
}

object TestLibraries {
    private object Versions {
        const val junit = "4.12"
        const val kluent = "1.68"
        const val mockk = "1.12.0"
        const val core = "2.1.0"
    }

    const val junit = "junit:junit:${Versions.junit}"
    const val kluent = "org.amshove.kluent:kluent:${Versions.kluent}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val archCoreTesting = "androidx.arch.core:core-testing:${Versions.core}"
    const val coroutinesCore = Libraries.coroutinesCore
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Libraries.Versions.coroutines}"
}