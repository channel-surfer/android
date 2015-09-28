package org.channelsurfer.android.posts

import android.graphics.Point
import android.os.SystemClock
import android.test.ActivityInstrumentationTestCase2
import android.view.View
import android.view.ViewGroup
import kotlin.test.assertEquals
import com.robotium.solo.Solo
import org.channelsurfer.android.base.Adapter

public class PostsFragmentTest : ActivityInstrumentationTestCase2<PostsActivity>(PostsActivity::class.java) {
    lateinit var solo: Solo
    lateinit var fragment: PostsFragment
    val size = Point()

    override fun setUp() {
        super.setUp()
        setActivityInitialTouchMode(true)
        fragment = activity.postsFragment
        solo = Solo(instrumentation, activity)
        activity.windowManager.defaultDisplay.getSize(size)
    }

    fun testSwipeToUpdate() {
        var calls = 0
        runTestOnUiThread {
            fragment.listAdapter = object : Adapter<Post>(arrayOf()) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? = null
                override fun update(callback: (Exception?) -> Unit) {
                    calls++
                    callback(null)
                }
            }
        }
        solo.drag(size.x.toFloat() / 2, size.x.toFloat() / 2, size.y.toFloat() / 2, size.y.toFloat(), 1)
        SystemClock.sleep(1000)
        assertEquals(calls, 1)
    }
}
