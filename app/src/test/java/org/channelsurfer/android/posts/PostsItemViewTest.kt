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
    var post = createSamplePost()

    @Test fun update() {
        val post2 = post.copy(com="Sample Body")
        val holder = PostsItemView.Holder(context)
        holder.update(post)
        assertEquals(holder.view.body.text, post.body)
        assertNotEquals(holder.view.body.text, post2.body)

        holder.update(post2)
        assertNotEquals(holder.view.body.text, post.body)
        assertEquals(holder.view.body.text, post2.body)
    }
}
