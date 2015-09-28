package org.channelsurfer.android.base

import org.channelsurfer.android.BuildConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
import org.robolectric.util.FragmentTestUtil.startVisibleFragment
import kotlin.test.assertEquals

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class)
public class SwipeRefreshListFragmentTest {
    val fragment = SwipeRefreshListFragment<Int>()

    init { startVisibleFragment(fragment) }

    @Test fun view() = assertEquals(fragment.view, fragment.swipeView)
}
