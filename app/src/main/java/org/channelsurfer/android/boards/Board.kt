package org.channelsurfer.android.boards

import java.io.Serializable
import java.util.*

public data class Board(
        val name: String,
        val title: String,
        private val uuid: UUID? = null) : Serializable {

    constructor() : this("", "")
}
