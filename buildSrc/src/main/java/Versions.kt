object Versions {
    const val coreKtxVersion = "1.7.0"
    const val kotlinStdLibVersion = "1.6.10"
    const val appcompatVersion = "1.4.1"
    const val materialVersion = "1.6.0"
    const val constraintLayoutVersion = "2.1.4"
    const val junitVersion = "4.13.2"
    const val junitAndroidTestVersion = "1.1.4"
    const val espressoVersion = "3.5.0"
    const val coroutinesCoreVersion = "1.5.2"
    const val coroutinesAndroidVersion = "1.6.1"
    const val retrofit2Version = "2.9.0"
    const val retrofitConverterVersion = "2.9.0"
    const val okhttp3LoggingInterceptorVersion = "4.9.0"
    const val koinAndroidVersion = "3.1.6"
    const val koinCoreVersion = "3.1.6"
    const val lifecycleLivedataVersion = "2.5.1"
    const val lifecycleViewModelVersion = "2.5.1"
    const val roomVersion = "2.4.3"
    const val glideVersion = "4.12.0"
    const val youtubePlayerVersion = "11.1.0"
    const val navigationVersion = "2.5.3"
}

object Dependencies {
    const val KOTLIN_STD_LIB_DEP =
        "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinStdLibVersion}"
    const val CORE_KTX_DEP = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val APPCOMPAT_DEP = "androidx.appcompat:appcompat:${Versions.appcompatVersion}"
    const val MATERIAL_DEP = "com.google.android.material:material:${Versions.materialVersion}"
    const val CONSTRAINT_DEP =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val JUNIT_DEP = "junit:junit:${Versions.junitVersion}"
    const val JUNIT_ANDROID_DEP = "androidx.test.ext:junit:${Versions.junitAndroidTestVersion}"
    const val ESPRESSO_CORE_DEP = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"

    // ViewModel
    const val VIEWMODEL_LIFECYCLE_KTX_DEP =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModelVersion}"

    // LiveData
    const val LIFECYCLE_LIVEDATA_KTX_DEP =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleLivedataVersion}"

    //Retrofit
    const val RETROFIT_DEP = "com.squareup.retrofit2:retrofit:${Versions.retrofit2Version}"
    const val RETROFIT_CONVERTER_DEP =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofitConverterVersion}"
    const val OKHTTP_LOGGING =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3LoggingInterceptorVersion}"

    //Glide
    const val GLIDE_DEP = "com.github.bumptech.glide:glide:${Versions.glideVersion}"

    //Room
    const val ROOM_RUNTIME_DEP = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val ROOM_RUNTIME_COMPILER_DEP = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val ROOM_KTX_DEP = "androidx.room:room-ktx:${Versions.roomVersion}"

    //Koin
    const val KOIN_ANDROID_DEP = "io.insert-koin:koin-android:${Versions.koinAndroidVersion}"
    const val KOIN_CORE_DEP = "io.insert-koin:koin-core:${Versions.koinCoreVersion}"

    //YoutubePlayer
    const val YOUTUBE_PLAYER_DEP =
        "com.pierfrancescosoffritti.androidyoutubeplayer:core:${Versions.youtubePlayerVersion}"

    //Navigation
    const val NAVIGATION_FRAGMENT_DEP =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val NAVIGATION_UI_DEP =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"

    //Coroutines
    const val COROUTINES_CORE_DEP =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCoreVersion}"
    const val COROUTINES_KOTLIN =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroidVersion}"
}