// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.44")
    }
}
plugins {
    id("com.android.application") version "8.1.0-alpha08" apply false
    id("com.android.library") version "8.1.0-alpha08" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
}