package org.channelsurfer.android.posts

import com.github.salomonbrys.kotson.fromJson
import com.github.salomonbrys.kotson.simpleDeserialize
import com.google.gson.Gson
import nl.qbusict.cupboard.DatabaseCompartment
import org.channelsurfer.android.base.Network
import org.channelsurfer.android.base.createGson
import org.channelsurfer.android.base.gson
import org.channelsurfer.android.boards.Board
import org.channelsurfer.android.database.entities

private val fetchGson: Gson = createGson {
    simpleDeserialize {
        it.asJsonArray.flatMap {
            it.asJsonObject.getAsJsonArray("threads").map { gson.fromJson<Post>(it)!! }
        }
    }
}

val Board.postsUrl: String get() = "https://8ch.net/$name/catalog.json"

var DatabaseCompartment.posts: List<Post>
    get() = entities { this }
    set(value) {
        delete(Post::class.java, null)
        put(value)
    }

fun Network.getPosts(board: Board, callback: (List<Post>?, Exception?) -> Unit) {
    string(url=board.postsUrl) { response, error ->
        callback(if(error == null && response != null) fetchGson.fromJson<List<Post>>(response) else null, error)
    }
}
