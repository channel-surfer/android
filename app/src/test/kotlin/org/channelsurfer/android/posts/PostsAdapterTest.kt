package org.channelsurfer.android.posts

import org.channelsurfer.android.BuildConfig
import org.channelsurfer.android.unixTime
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class)
public class PostsAdapterTest {
    val adapter = PostsAdapter(RuntimeEnvironment.application.applicationContext)

    @Test fun getView() {
        adapter.data += SamplePost()
        adapter.data += SamplePost(time=adapter.data.last().createdAt.unixTime+1000)
        val view1 = adapter.getView(0, null, null)
        assertTrue(view1 is PostsItemView)
        assertEquals((view1 as PostsItemView).post, adapter.data[0])

        val view2 = adapter.getView(0, null, null)
        assertTrue(view2 is PostsItemView)
        assertNotEquals(view1, view2)
        assertEquals((view2 as PostsItemView).post, adapter.data[0])
        assertEquals(view1.post, view2.post)

        val view3 = adapter.getView(1, view2, null)
        assertTrue(view3 is PostsItemView)
        assertEquals(view2, view3)
        assertEquals((view3 as PostsItemView).post, adapter.data[1])
        assertNotEquals(view1.post, view3.post)
        assertEquals(view2.post, view3.post)
    }
}
