package org.channelsurfer.android.posts

import android.content.Context
import com.github.salomonbrys.kotson.fromJson
import com.github.salomonbrys.kotson.simpleDeserialize
import com.google.gson.Gson
import org.channelsurfer.android.base.createGson
import org.channelsurfer.android.base.globalNetwork
import org.channelsurfer.android.boards.Board

private val gson: Gson = createGson {
    simpleDeserialize {
        it.asJsonArray.flatMap {
            it.asJsonObject.getAsJsonArray("threads").map { gson.fromJson<Post>(it)!! }
        }
    }
}

private val Board.postsUrl : String get() = "https://8ch.net/$name/catalog.json"

// TODO Change later to be a Network extension instead of Context extension
fun Context.fetchPosts(board: Board, callback: (List<Post>?, Exception?) -> Unit) {
    globalNetwork.string(url=board.postsUrl) { response, error ->
        val posts = if(error == null && response != null) gson.fromJson<List<Post>>(response) else null
        // TODO Remove later as response is temporary caching. This also means context is no longer directly needed
        if(posts != null) {
            val editor = getSharedPreferences("threads", Context.MODE_PRIVATE).edit()
            editor.putString("threads", response)
            editor.commit()
        }
        callback(posts, error)
    }
}

// TODO Remove later as this is temporary caching
val Context.cachedPosts: List<Post> get() {
    val string = getSharedPreferences("threads", Context.MODE_PRIVATE).getString("threads", "[]")
    return gson.fromJson<List<Post>>(string)!!
}
