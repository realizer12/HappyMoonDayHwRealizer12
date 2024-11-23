plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.happymoonday.local"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
}

dependencies {

    implementation(libs.bundles.androidx.room)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}
