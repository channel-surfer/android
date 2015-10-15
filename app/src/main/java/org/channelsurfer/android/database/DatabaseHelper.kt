package org.channelsurfer.android.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.support.ConnectionSource
import org.channelsurfer.android.boards.Board
import org.channelsurfer.android.posts.Post
import java.util.*

public class DatabaseHelper(context: Context) : OrmLiteSqliteOpenHelper(context, FILENAME, null, VERSION) {
    @Suppress("UNCHECKED_CAST") val boards by lazy { getDao(Board::class.java) as Dao<Board, UUID> }
    @Suppress("UNCHECKED_CAST") val posts by lazy { getDao(Post::class.java) as Dao<Post, UUID> }

    override fun onCreate(database: SQLiteDatabase, connectionSource: ConnectionSource) {
        connectionSource.createTable<Post>()
        connectionSource.createTable<Board>()
    }

    override fun onUpgrade(database: SQLiteDatabase, connectionSource: ConnectionSource, oldVersion: Int, newVersion: Int) {}
}
