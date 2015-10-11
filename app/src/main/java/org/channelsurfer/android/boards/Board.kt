package org.channelsurfer.android.boards

import java.io.Serializable

public data class Board(
        private var id: String = "",
        val title: String) : Serializable {
    var name: String
        get() = if(id != "") id else throw Exception("No name exists")
        set(value) { if(id == "") id = value else throw Exception("Name already exists exists") }

    constructor() : this(title="")
}
