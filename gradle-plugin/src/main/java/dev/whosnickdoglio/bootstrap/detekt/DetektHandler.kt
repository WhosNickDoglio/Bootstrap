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

import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

/** Handler for configuring detekt. */
open class DetektHandler @Inject constructor(objects: ObjectFactory) {

    internal val isFormattingEnabled: Property<Boolean> = objects.property(Boolean::class.java).convention(true)

    internal val version: Property<String> = objects.property(String::class.java).convention(DEFAULT_DETEKT_VERSION)

    fun enableDetektFormatting(isEnabled: Boolean) {
        this.isFormattingEnabled.set(isEnabled)
        this.isFormattingEnabled.disallowChanges()
    }

    fun version(version: String) {
        this.version.set(version)
        this.version.disallowChanges()
    }

    internal companion object {
        internal const val DEFAULT_DETEKT_VERSION = "1.22.0"
    }
}

internal fun Project.configureDetekt(detekt: DetektHandler) {
    project.plugins.apply("io.gitlab.arturbosch.detekt")
    if (detekt.isFormattingEnabled.get()) {
        project.dependencies.add(
            "detektPlugins",
            "io.gitlab.arturbosch.detekt:detekt-formatting:${detekt.version.get()}"
        )
    }
}
