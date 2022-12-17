/*
 *   MIT License
 *
 *   Copyright (c) 2022 Nicholas Doglio
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in all
 *   copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 */

package dev.whosnickdoglio.bootstrap

import com.google.common.truth.Truth.assertThat
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

class BootstrapPluginTest {

    @Test
    fun `android app module has cache fix plugin applied`() {
        val project = ProjectBuilder.builder().build()

        project.pluginManager.apply("dev.whosnickdoglio.bootstrap")
        project.pluginManager.apply("com.android.application")

        assertThat(project.pluginManager.hasPlugin("org.gradle.android.cache-fix")).isTrue()
    }

    @Test
    fun `android library module has cache fix plugin applied`() {
        val project = ProjectBuilder.builder().build()

        project.pluginManager.apply("dev.whosnickdoglio.bootstrap")
        project.pluginManager.apply("com.android.library")

        assertThat(project.pluginManager.hasPlugin("org.gradle.android.cache-fix")).isTrue()
    }
}
