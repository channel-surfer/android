package org.channelsurfer.android.posts

import org.channelsurfer.android.BuildConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
import kotlin.test.*

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class)
    public class PostsActivityTest {
    @Test fun title() {
        val activity = Robolectric.setupActivity(PostsActivity::class.java)
        assertEquals(activity.supportActionBar.title, "Channel Surfer")
    }
}
