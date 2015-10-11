package org.channelsurfer.android.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

public class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, FILENAME, null, VERSION) {
    val database by lazy { cupboard.withDatabase(writableDatabase) }

    override fun onCreate(db: SQLiteDatabase) {
        cupboard.withDatabase(db).createTables()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        cupboard.withDatabase(db).upgradeTables()
    }
}
