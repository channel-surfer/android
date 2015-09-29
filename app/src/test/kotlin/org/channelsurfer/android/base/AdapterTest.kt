package org.channelsurfer.android.base

import android.content.Context
import android.os.SystemClock
import android.view.ViewGroup
import android.widget.TextView
import org.channelsurfer.android.BuildConfig
import org.jetbrains.anko.async
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class)
public class AdapterTest {
    val adapter = object : Adapter<Int, ViewHolder>(arrayOf()) {
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            return ViewHolder(RuntimeEnvironment.application.applicationContext)
        }
        override fun update(callback: (Exception?) -> Unit) {
            updatedCount++
            callback(null)
        }
    }
    var updatedCount = 0

    @Before fun setup() {
        adapter.data = arrayOf()
        updatedCount = 0
    }

    // TODO Figure out why notifyDataSetChanged is not triggered here
    @Ignore @Test fun update() {
        assertEquals(updatedCount, 0)

        async { adapter.data = arrayOf(1) }
        SystemClock.sleep(1000)
        assertEquals(updatedCount, 1)

        async { adapter.data = arrayOf() }
        SystemClock.sleep(1000)
        assertEquals(updatedCount, 2)
    }

    @Test fun properties() {
        assertEquals(adapter.data.size(), adapter.itemCount)
        adapter.data += arrayOf(5, 6, 7)
        assertEquals(adapter.data.size(), adapter.itemCount)
    }

    class ViewHolder(val view: TextView) : org.channelsurfer.android.base.ViewHolder<Int, TextView>(view) {
        constructor(context: Context) : this(TextView(context))

        override fun update(data: Int) {
            view.text = data.toString()
        }
    }
}
