package org.channelsurfer.android

import android.app.Application
import android.test.ApplicationTestCase
import kotlin.test.*
import kotlin.text.Regex

final public class ApplicationTest : ApplicationTestCase<Application>(Application::class.java) {
    override fun setUp() {
        super.setUp()
        createApplication()
    }

    fun testCorrectVersion() {
        val info = application.packageManager.getPackageInfo(application.packageName, 0)
        assertTrue(Regex("\\d\\.\\d").matches(info.versionName))
    }
}
