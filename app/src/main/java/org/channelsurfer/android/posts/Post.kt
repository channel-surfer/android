package org.channelsurfer.android.posts

import org.channelsurfer.android.base.fromHtml
import java.io.Serializable
import java.util.*

public data class Post(
        private val no: Int,
        private val com: String,
        private val email: String?,
        private val name: String,
        private val capcode: String?,
        private val trip: String?,
        private val sub: String?,
        private val time: Int,
        val replies: Int,
        private val sticky: Int,
        private val locked: Int,
        private val lastModified: Int) : Serializable {
    @Transient val id by lazy { no }
    @Transient val title by lazy { sub?.fromHtml }
    @Transient val isSticky by lazy { sticky == 1 }
    @Transient val isLocked by lazy { locked == 1 }
    @Transient val createdAt by lazy { Date(time.toLong() * 1000) }
    @Transient val updatedAt by lazy { Date(lastModified.toLong() * 1000) }
    @Transient val body by lazy { com.fromHtml }
    @Transient val header by lazy {
        var header = name
        if(trip != null) header += trip
        if(email != null) header = "<a href=\"mailto:$email\">$header</a>"
        if(capcode != null) header += " ## $capcode"
        header = "<b>$id</b> - $header"
        header.fromHtml
    }

    constructor() : this(0, "", null, "", null, null, null, 0, 0, 0, 0, 0)
}
