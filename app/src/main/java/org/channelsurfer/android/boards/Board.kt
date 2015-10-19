package org.channelsurfer.android.boards

import com.j256.ormlite.field.DatabaseField
import org.channelsurfer.android.base.unixTime
import java.io.Serializable
import java.util.*

public data class Board(
        @DatabaseField val name: String,
        @DatabaseField val title: String,
        @DatabaseField(id=true) val id: String = UUID.randomUUID().toString(),
        @DatabaseField private val lastModified: Int = Date().unixTime) : Serializable {
    @Transient val updatedAt by lazy { Date(lastModified.toLong() * 1000) }

    constructor() : this("", "")
}
