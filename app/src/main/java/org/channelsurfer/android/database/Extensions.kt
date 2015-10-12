package org.channelsurfer.android.database

import android.content.Context
import nl.qbusict.cupboard.CupboardFactory
import nl.qbusict.cupboard.DatabaseCompartment
import nl.qbusict.cupboard.DatabaseCompartment.QueryBuilder
import nl.qbusict.cupboard.QueryResultIterable
import org.channelsurfer.android.boards.Board
import org.channelsurfer.android.posts.Post

val FILENAME = "database.db"
val VERSION = 1

private var globalDatabase: DatabaseCompartment? = null

val cupboard = with(CupboardFactory.cupboard()) {
    register(Post::class.java)
    register(Board::class.java)
    this
}

val Context.database: DatabaseCompartment get() {
    globalDatabase = globalDatabase ?: DatabaseHelper(applicationContext).database
    return globalDatabase!!
}

inline fun <reified T : Any> DatabaseCompartment.entities(builder: QueryBuilder<T>.() -> QueryBuilder<T>): List<T> {
    val cursor = query(T::class.java).builder().cursor
    val boards: List<T>
    var iterator: QueryResultIterable<T>? = null
    try {
        iterator = cupboard.withCursor(cursor).iterate(T::class.java)
        boards = iterator.list()
    }
    finally {
        iterator?.close()
    }
    return boards
}

