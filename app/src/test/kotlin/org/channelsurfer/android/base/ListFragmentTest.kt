package org.channelsurfer.android.base

import android.view.View
import android.view.ViewGroup
import org.channelsurfer.android.BuildConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
import org.robolectric.util.FragmentTestUtil.startVisibleFragment
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class)
public class ListFragmentTest {
    val fragment = ListFragment<Int>()

    init {
        fragment.listAdapter = object : Adapter<Int>(arrayOf()) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? = null
        }
        startVisibleFragment(fragment)
    }

    @Test fun retainInstance() = assertTrue(fragment.retainInstance)

    @Test fun setOnItemClickListener() {
        fragment.listAdapter.data += 1
        fragment.setOnItemClickListener {
            assertEquals(it, 1)
        }
        fragment.listView.performItemClick(fragment.listAdapter.getView(0, null, null), 0, 0)
    }
}
