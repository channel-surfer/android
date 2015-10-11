package org.channelsurfer.android.database

import android.content.Context
import nl.qbusict.cupboard.CupboardFactory
import nl.qbusict.cupboard.DatabaseCompartment

val FILENAME = "database.db"
val VERSION = 1

private var globalDatabase: DatabaseCompartment? = null

val cupboard = CupboardFactory.cupboard()

val Context.database: DatabaseCompartment get() {
    globalDatabase = globalDatabase ?: DatabaseHelper(applicationContext).database
    return globalDatabase!!
}
