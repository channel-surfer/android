package org.channelsurfer.android.posts

import org.channelsurfer.android.base.unixTime
import java.util.*

public class SamplePost(
        no: Int = 0,
        com: String = "Sample",
        email: String? = null,
        name: String = "Anonymous",
        capcode: String? = null,
        trip: String? = null,
        sub: String? = null,
        time: Int = Date().unixTime,
        replies: Int = 0,
        sticky: Int = 0,
        locked: Int = 0,
        lastModified: Int = time) : Post(no, com, email, name, capcode, trip, sub, time, replies, sticky, locked, lastModified)
