package org.channelsurfer.android.posts

import com.github.salomonbrys.kotson.fromJson
import org.channelsurfer.android.BuildConfig
import org.channelsurfer.android.base.unixTime
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
import java.util.*
import kotlin.test.*

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class)
public class PostTest {
    @Test fun json() {
        val now = Date()
        val json = """
        {
          "no": 1,
          "com": "Sample 2",
          "name": "Anonymous",
          "time": ${now.unixTime},
          "replies": 2,
          "sticky": 1,
          "locked": 1,
          "last_modified": ${now.unixTime}
        }
        """

        val post = gson.fromJson<Post>(json)!!
        assertEquals(post.id, 1)
        assertEquals(post.body.toString(), "Sample 2")
        assertEquals(post.createdAt.unixTime, now.unixTime)
        assertEquals(post.replies, 2)
        assertTrue(post.isSticky)
        assertTrue(post.isLocked)
        assertEquals(post.updatedAt.unixTime, now.unixTime)
    }
}
