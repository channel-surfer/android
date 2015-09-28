package org.channelsurfer.android.posts

import org.channelsurfer.android.unixTime
import java.util.*

public class SamplePost(
        no: Int = 0,
        com: String = "Sample",
        sub: String? = null,
        time: Int = Date().unixTime,
        replies: Int = 0,
        sticky: Int = 0,
        locked: Int = 0,
        lastModified: Int = time) : Post(no, com, sub, time, replies, sticky, locked, lastModified)
