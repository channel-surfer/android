package org.channelsurfer.android.posts

import com.j256.ormlite.field.DatabaseField
import org.channelsurfer.android.base.fromHtml
import java.io.Serializable
import java.util.*

public data class Post(
        @DatabaseField private val no: Int,
        @DatabaseField private val com: String,
        @DatabaseField private val email: String?,
        @DatabaseField private val name: String,
        @DatabaseField private val capcode: String?,
        @DatabaseField private val trip: String?,
        @DatabaseField private val sub: String?,
        @DatabaseField private val time: Int,
        @DatabaseField val replies: Int,
        @DatabaseField private val sticky: Int,
        @DatabaseField private val locked: Int,
        @DatabaseField private val lastModified: Int,
        @DatabaseField(generatedId=true) private val uuid: UUID? = null,
        @DatabaseField private val save: Boolean = false) : Serializable {
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
