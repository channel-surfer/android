package org.channelsurfer.android.database

import android.content.Context
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils

val FILENAME = "database.db"
val VERSION = 1

private var globalDatabase: DatabaseHelper? = null

val Context.database: DatabaseHelper get() {
    globalDatabase = globalDatabase ?: DatabaseHelper(applicationContext)
    return globalDatabase!!
}

var <T> Dao<T, String>.all: List<T>
    get() = queryForAll()
    set(value) { callBatchTasks {
        deleteBuilder().where().isNotNull("id")
        deleteBuilder().delete()
        value.forEach { createOrUpdate(it) }
    }}

inline fun <reified T : Any> ConnectionSource.createTable() = TableUtils.createTable(this, T::class.java);

operator fun <T, ID> Dao<T, ID>.get(id: ID) = queryForId(id)

operator fun <T> Dao<T, *>.plusAssign(row: T) {
    createOrUpdate(row)
}
