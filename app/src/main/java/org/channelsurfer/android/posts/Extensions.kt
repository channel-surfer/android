package org.channelsurfer.android.posts

import com.github.salomonbrys.kotson.fromJson
import com.github.salomonbrys.kotson.simpleDeserialize
import com.google.gson.Gson
import org.channelsurfer.android.base.Network
import org.channelsurfer.android.base.createGson
import org.channelsurfer.android.base.gson
import org.channelsurfer.android.boards.Board

private val fetchGson: Gson = createGson {
    simpleDeserialize {
        it.asJsonArray.flatMap {
            it.asJsonObject.getAsJsonArray("threads").map { gson.fromJson<Post>(it)!! }
        }
    }
}

private val Board.postsUrl : String get() = "https://8ch.net/$name/catalog.json"

fun Network.fetchPosts(board: Board, callback: (List<Post>?, Exception?) -> Unit) {
    string(url=board.postsUrl) { response, error ->
        callback(if(error == null && response != null) fetchGson.fromJson<List<Post>>(response) else null, error)
    }
}
