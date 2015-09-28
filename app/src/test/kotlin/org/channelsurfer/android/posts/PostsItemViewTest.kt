package org.channelsurfer.android.posts

import org.channelsurfer.android.BuildConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class)
public class PostsItemViewTest {
    var context = RuntimeEnvironment.application.applicationContext
    var post = SamplePost()

    @Test fun init() {
        val view = PostsItemView(context, post)
        assertEquals(view.titleView.text, post.title)
    }

    @Test fun update() {
        val post2 = post.copy(sub="Sample Sub")
        val view = PostsItemView(context, post)
        assertEquals(view.titleView.text, post.title)
        assertNotEquals(view.titleView.text, post2.title)

        view.post = post2
        assertNotEquals(view.titleView.text, post.title)
        assertEquals(view.titleView.text, post2.title)
    }
}
