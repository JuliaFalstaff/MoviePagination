import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
}

android {
    compileSdk = 31
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.example.moviepagination"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schema")
            }
        }
    }

    buildTypes.onEach {
        val properties = Properties()
        properties.load(project.rootProject.file("apikey.properties").inputStream())
        val imdbApiKey = properties.getProperty("IMDb_API_KEY", "")
        it.buildConfigField("String", "IMDb_API_KEY", imdbApiKey)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Dependencies.KOTLIN_STD_LIB_DEP)
    implementation(Dependencies.CORE_KTX_DEP)
    implementation(Dependencies.APPCOMPAT_DEP)
    implementation(Dependencies.MATERIAL_DEP)
    implementation(Dependencies.CONSTRAINT_DEP)
    testImplementation(Dependencies.JUNIT_DEP)
    androidTestImplementation(Dependencies.JUNIT_ANDROID_DEP)
    androidTestImplementation(Dependencies.ESPRESSO_CORE_DEP)
    implementation(Dependencies.LIFECYCLE_LIVEDATA_KTX_DEP)
    implementation(Dependencies.VIEWMODEL_LIFECYCLE_KTX_DEP)

    //Retrofit
    implementation(Dependencies.RETROFIT_DEP)
    implementation(Dependencies.RETROFIT_CONVERTER_DEP)
    implementation(Dependencies.OKHTTP_LOGGING)

    //Glide
    implementation(Dependencies.GLIDE_DEP)

    //Room
    implementation(Dependencies.ROOM_RUNTIME_DEP)
    kapt(Dependencies.ROOM_RUNTIME_COMPILER_DEP)
    implementation(Dependencies.ROOM_KTX_DEP)

    //Koin
    implementation(Dependencies.KOIN_ANDROID_DEP)
    implementation(Dependencies.KOIN_CORE_DEP)

    //YoutubePlayer
    implementation(Dependencies.YOUTUBE_PLAYER_DEP)

    //Navigation
    implementation(Dependencies.NAVIGATION_FRAGMENT_DEP)
    implementation(Dependencies.NAVIGATION_UI_DEP)

    //Coroutines
    implementation(Dependencies.COROUTINES_CORE_DEP)
    implementation(Dependencies.COROUTINES_KOTLIN)

    //Shimmer
    implementation(Dependencies.SHIMMER_DEP)
}