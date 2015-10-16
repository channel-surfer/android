package org.channelsurfer.android.boards

import com.j256.ormlite.field.DatabaseField
import java.io.Serializable
import java.util.*

public data class Board(
        @DatabaseField val name: String,
        @DatabaseField val title: String,
        @DatabaseField(generatedId=true) private val uuid: UUID? = null) : Serializable {

    constructor() : this("", "")
}
