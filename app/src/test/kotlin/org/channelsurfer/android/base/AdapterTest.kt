package org.channelsurfer.android.base

import android.database.DataSetObserver
import android.view.View
import android.view.ViewGroup
import org.channelsurfer.android.BuildConfig
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class)
public class AdapterTest {
    lateinit var adapter: Adapter<Int>

    @Before fun setup() {
        adapter = object : Adapter<Int>(arrayOf()) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? = null
        }
    }

    @Test fun update() {
        var count = 0
        adapter.registerDataSetObserver(object : DataSetObserver() {
            override fun onChanged() {
                super.onChanged()
                count++
            }
        })
        assertEquals(count, 0)
        adapter.data = arrayOf(1)
        assertEquals(count, 1)
        adapter.data = arrayOf()
        assertEquals(count, 2)
    }

    @Test fun properties() {
        assertEquals(adapter.data.size(), adapter.count)
        adapter.data += arrayOf(5, 6, 7)
        assertEquals(adapter.data.size(), adapter.count)
        adapter.data.forEachIndexed { index, item ->
            assertEquals(adapter.getItem(index), item)
            assertEquals(adapter.getItemId(index), index.toLong())
        }
    }
}
