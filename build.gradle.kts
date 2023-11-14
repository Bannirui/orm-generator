plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.15.0"
}

group = "com.github.bannirui"
version = "1.0.3"

repositories {
    mavenCentral()
}

dependencies {
	implementation("org.freemarker:freemarker:2.3.32")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2022.2.5")
	// IU supports database tool
    type.set("IU") // Target IDE Platform

    plugins.set(listOf(
			"com.intellij.java",
			"com.intellij.database"
	))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    patchPluginXml {
        sinceBuild.set("222")
        untilBuild.set("232.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
