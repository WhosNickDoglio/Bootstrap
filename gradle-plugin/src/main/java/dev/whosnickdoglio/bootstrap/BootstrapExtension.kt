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

import dev.whosnickdoglio.bootstrap.detekt.DetektHandler
import dev.whosnickdoglio.bootstrap.detekt.configureDetekt
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import javax.inject.Inject

/**
 * A Gradle plugin extension for the [BootstrapPlugin] that allows for
 * configuration.
 */
open class BootstrapExtension @Inject constructor(private val project: Project, objects: ObjectFactory) {

    internal val detekt: DetektHandler = objects.newInstance(DetektHandler::class.java)

    /** A detekt function that allows for configuration of Detekt. */
    fun detekt(action: Action<DetektHandler>? = null) {
        action?.execute(detekt)
        project.configureDetekt(detekt)
    }

    internal companion object {
        internal fun Project.createBootstrap(): BootstrapExtension =
            extensions.create("bootstrap", BootstrapExtension::class.java)
    }
}
