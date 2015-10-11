package org.channelsurfer.android.boards

import java.io.Serializable

public data class Board(
        val name: String,
        val title: String) : Serializable {
    constructor() : this("", "")
}
