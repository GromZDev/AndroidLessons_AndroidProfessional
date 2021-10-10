import org.gradle.api.JavaVersion

object Config {
    const val application_id = "q4_android_professional.myapplication"
    const val build_tools = "30.0.3"
    const val compile_sdk = 30
    const val min_sdk_ver = 25
    const val target_sdk = 30
    val java_version = JavaVersion.VERSION_1_8
    const val test_runner = "androidx.test.runner.AndroidJUnitRunner"
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"

    //Features
    const val historyScreen = ":historyScreen"
}

object Versions {

    // for Design
    const val appcompat = "1.3.1"
    const val material = "1.4.0"
    const val constraint_layout = "2.1.1"
    const val swipe_refresh = "1.1.0"

    // for Kotlin
    const val stdlib = "1.5.21"
    const val core = "1.6.0"
    const val jvm_target = "1.8"

    // for Rx
    const val rx_android ="2.1.0"
    const val rx_java ="2.2.9"

    // for Retrofit
    const val retrofit = "2.9.0"
    const val converter_gson = "2.9.0"
    const val rx_java3 = "2.9.0"
    const val rx_java2 = "1.0.0"
    const val interceptor = "3.12.2"
    const val adapter_coroutines = "0.9.2"

    // for Glide
    const val glide = "4.12.0"
    const val play = "17.0.1"
    const val compile = "4.12.0"

    // for Dagger
    const val dagger_android = "2.37"
    const val android_processor = "2.35.1"
    const val dagger_compile = "2.37"

    // for Koin
    const val koin_core = "3.1.2"
    const val koin_android = "3.1.2"
    const val koin_android_compat = "3.1.2"

    // for Coroutines
    const val coroutines_core = "1.5.1"
    const val coroutines_android = "1.5.1"

    // for Picasso
    const val picasso = "2.71828"

    // for Room
    const val room_ktx = "2.3.0"
    const val runtime = "2.3.0"
    const val room_compiler = "2.3.0"

    // for Test
    const val jUnit = "4.13.2"
    const val ext = "1.1.3"
    const val espressoCore = "3.4.0"
}

object General {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    const val swipe = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipe_refresh}"
}

object Kotlin {
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.stdlib}"
    const val core = "androidx.core:core-ktx:${Versions.core}"
}

object Rx {
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rx_android}"
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rx_java}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.converter_gson}"
    const val adapterRx3 = "com.squareup.retrofit2:adapter-rxjava3:${Versions.rx_java3}"
    const val adapterRx2 = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.rx_java2}"
    const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
    const val coroutines = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.adapter_coroutines}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val playServices = "com.google.android.gms:play-services-maps:${Versions.play}"
    const val compiler = "com.github.bumptech.glide:compiler:${Versions.compile}"
}

object Dagger {
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger_android}"
    const val androidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger_android}"
    const val kaptProcessor = "com.google.dagger:dagger-android-processor:${Versions.android_processor}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger_compile}"
}

object Koin {
    const val koinCore = "io.insert-koin:koin-core:${Versions.koin_core}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin_android}"
    const val koinAndroidCompat = "io.insert-koin:koin-android-compat:${Versions.koin_android_compat}"

}

object Coroutines {
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_core}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_android}"
}

object Picasso {
    const val picassoVersion = "com.squareup.picasso:picasso:${Versions.picasso}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.runtime}"
    const val compiler = "androidx.room:room-compiler:${Versions.room_compiler}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room_ktx}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val ext = "androidx.test.ext:junit:${Versions.ext}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}
