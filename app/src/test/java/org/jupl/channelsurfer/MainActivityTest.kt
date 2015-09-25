package org.jupl.channelsurfer

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
import kotlin.test.*

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class)
final public class MainActivityTest {
    @Test fun text() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        assertEquals(activity.helloTextView.text, "Hello world!")
    }
}
