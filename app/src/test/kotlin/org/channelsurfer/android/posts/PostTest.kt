package org.channelsurfer.android.posts

import android.os.Parcel
import org.channelsurfer.android.BuildConfig
import org.channelsurfer.android.unixTime
import org.json.JSONObject
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
        val json = JSONObject()
        val now = Date()
        json.put("no", 1)
        json.put("com", "Sample 2")
        json.put("time", now.unixTime)
        json.put("replies", 2)
        json.put("sticky", 1)
        json.put("locked", 1)
        json.put("last_modified", now.unixTime)

        val post = Post.fromJSON(json)
        assertEquals(post.id, 1)
        assertEquals(post.body, "Sample 2")
        assertEquals(post.createdAt.unixTime, now.unixTime)
        assertEquals(post.replies, 2)
        assertTrue(post.isSticky)
        assertTrue(post.isLocked)
        assertEquals(post.updatedAt.unixTime, now.unixTime)
    }

    @Test fun parcel() {
        val parcel = Parcel.obtain()
        val post1 = SamplePost()
        post1.writeToParcel(parcel, flags=0)
        parcel.setDataPosition(0)

        val post2 = Post.CREATOR.createFromParcel(parcel)
        assertEquals(post1, post2)
    }
}
