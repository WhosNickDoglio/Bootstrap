/*
 * MIT License
 *
 * Copyright (c) 2022 Nicholas Doglio
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.detekt)
    alias(libs.plugins.bestPractices)
    id("java-gradle-plugin")
    jacoco
}

jacoco {
    version = libs.versions.jacoco.get()
}


tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
    }
    dependsOn(tasks.test) // tests are required to run before generating the report
}



group = "dev.whosnickdoglio"
version = "1.0-SNAPSHOT"

gradlePlugin {
    plugins {
        create("bootstrap") {
            id = "dev.whosnickdoglio.bootstrap"
            implementationClass = "dev.whosnickdoglio.bootstrap.BootstrapPlugin"
        }
    }
}

dependencies {
    detektPlugins(libs.detekt.formatting)

    // TODO figure out how to best do this
    compileOnly(gradleApi())
    implementation(libs.gradleAndroidCacheFix)
    compileOnly(libs.gradle.kotlin)
    compileOnly(libs.gradle.detekt)
    api(libs.gradle.android)

    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.truth.testkit)
}
