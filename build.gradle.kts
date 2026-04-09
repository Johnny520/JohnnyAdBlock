plugins {
    id("com.android.application") version "8.2.2" apply false
}

tasks.register<Delete>("clean") {
    delete(layout.buildDirectory)
}
