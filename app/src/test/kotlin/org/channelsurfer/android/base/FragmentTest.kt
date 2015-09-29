package org.channelsurfer.android.base

import org.channelsurfer.android.BuildConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
import org.robolectric.util.FragmentTestUtil
import kotlin.test.assertTrue

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class)
public class FragmentTest {
    var fragment = Fragment()

    init { FragmentTestUtil.startFragment(fragment) }

    @Test fun retainInstance() = assertTrue(fragment.retainInstance)
    @Test fun activityCreated() = assertTrue(fragment.activityCreated)
}
