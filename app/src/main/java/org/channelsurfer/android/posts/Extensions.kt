package org.channelsurfer.android.posts

import com.github.salomonbrys.kotson.fromJson
import com.github.salomonbrys.kotson.simpleDeserialize
import com.google.gson.Gson
import com.j256.ormlite.dao.Dao
import org.channelsurfer.android.base.Network
import org.channelsurfer.android.base.createGson
import org.channelsurfer.android.base.gson
import org.channelsurfer.android.boards.Board
import org.channelsurfer.android.database.all

private val fetchGson: Gson = createGson {
    simpleDeserialize {
        it.asJsonArray.flatMap {
            it.asJsonObject.getAsJsonArray("threads").map { gson.fromJson<Post>(it) }
        }
    }
}

operator fun Dao<Post, *>.get(board: Board): List<Post> = queryForEq("board_id", board.id)

operator fun Dao<Post, *>.set(board: Board, posts: List<Post>) = callBatchTasks {
    deleteBuilder().where().eq("board_id", board.id).and().eq("save", false)
    deleteBuilder().delete()
    all += posts.map { it.copy(board=board) }
}

val Board.postsUrl: String get() = "https://8ch.net/$name/catalog.json"

fun Network.getPosts(board: Board, callback: (List<Post>?, Exception?) -> Unit) {
    string(url=board.postsUrl) { response, error ->
        callback(if(error == null && response != null) fetchGson.fromJson<List<Post>>(response) else null, error)
    }
}
