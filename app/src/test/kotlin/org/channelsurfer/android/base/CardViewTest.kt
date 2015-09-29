package org.channelsurfer.android.base

import org.channelsurfer.android.BuildConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.assertTrue

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class)
public class CardViewTest {
    var cardView = CardView(RuntimeEnvironment.application.applicationContext)

    @Test fun useCompatPadding() = assertTrue(cardView.useCompatPadding)
}
