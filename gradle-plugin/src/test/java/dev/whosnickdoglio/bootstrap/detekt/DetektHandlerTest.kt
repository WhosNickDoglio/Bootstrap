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

package dev.whosnickdoglio.bootstrap.detekt

import com.google.common.truth.Truth.assertThat
import dev.whosnickdoglio.bootstrap.BootstrapExtension
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

class DetektHandlerTest {

    @Test
    fun `detekt is applied when enabled`() {
        val project = ProjectBuilder.builder().build()

        project.pluginManager.apply("dev.whosnickdoglio.bootstrap")

        project.extensions.configure(BootstrapExtension::class.java) { bootstrap ->
            bootstrap.detekt()
        }

        val extension = project.extensions.findByType(BootstrapExtension::class.java)

        assertThat(project.pluginManager.hasPlugin("io.gitlab.arturbosch.detekt")).isTrue()
    }

    @Test
    fun `detekt is not applied when disabled`() {
        val project = ProjectBuilder.builder().build()

        project.pluginManager.apply("dev.whosnickdoglio.bootstrap")

        assertThat(project.pluginManager.hasPlugin("io.gitlab.arturbosch.detekt")).isFalse()
    }

    @Test
    fun `detekt formatting is enabled by default`() {
        val project = ProjectBuilder.builder().build()

        project.pluginManager.apply("dev.whosnickdoglio.bootstrap")

        project.extensions.configure(BootstrapExtension::class.java) { bootstrap ->
            bootstrap.detekt()
        }

        val extension = project.extensions.findByType(BootstrapExtension::class.java)

        assertThat(extension?.detekt?.isFormattingEnabled?.get() == true).isTrue()
    }

    @Test
    fun `detekt formatting is not added when disabled`() {
        val project = ProjectBuilder.builder().build()

        project.pluginManager.apply("dev.whosnickdoglio.bootstrap")

        project.extensions.configure(BootstrapExtension::class.java) { bootstrap ->
            bootstrap.detekt {
                it.enableDetektFormatting(false)
            }
        }

        val extension = project.extensions.findByType(BootstrapExtension::class.java)
        assertThat(extension?.detekt?.isFormattingEnabled?.get() == false).isTrue()
    }

    @Test
    fun `the default version of detekt is applied when no other version is specified`() {
        val project = ProjectBuilder.builder().build()

        project.pluginManager.apply("dev.whosnickdoglio.bootstrap")

        project.extensions.configure(BootstrapExtension::class.java) { bootstrap ->
            bootstrap.detekt()
        }

        val extension = project.extensions.findByType(BootstrapExtension::class.java)

        assertThat(extension?.detekt?.version?.get().orEmpty()).isEqualTo(DetektHandler.DEFAULT_DETEKT_VERSION)
    }

    @Test
    fun `when a detekt version is specified it is used instead of the default`() {
        val project = ProjectBuilder.builder().build()

        project.pluginManager.apply("dev.whosnickdoglio.bootstrap")

        val newDetektVersion = "1.20.0"

        project.extensions.configure(BootstrapExtension::class.java) { bootstrap ->
            bootstrap.detekt {
                it.version(newDetektVersion)
            }
        }

        val extension = project.extensions.findByType(BootstrapExtension::class.java)

        assertThat(extension?.detekt?.version?.get().orEmpty()).isEqualTo(newDetektVersion)
    }
}
