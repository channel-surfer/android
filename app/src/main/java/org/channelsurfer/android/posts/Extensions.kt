package org.channelsurfer.android.posts

import android.content.Context
import com.github.salomonbrys.kotson.fromJson
import com.github.salomonbrys.kotson.simpleDeserialize
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.channelsurfer.android.base.*

val gson: Gson = run {
    val builder = GsonBuilder()
    builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    builder.simpleDeserialize {
        it.asJsonArray.flatMap {
            it.asJsonObject.getAsJsonArray("threads").map { gson.fromJson<Post>(it)!! }
        }.toTypedArray()
    }
    builder.create()
}

// TODO Change later to be a Network extension instead of Context extension
fun Context.fetchPosts(callback: (Array<Post>?, Exception?) -> Unit) {
    globalNetwork.string(url="https://8ch.net/tech/catalog.json") { response, error ->
        if(error == null && response != null) {
            val posts = gson.fromJson<Array<Post>>(response)
            // TODO Remove later as this is temporary caching. This also means context is no longer directly needed
            val editor = getSharedPreferences("threads", Context.MODE_PRIVATE).edit()
            editor.putString("threads", response)
            editor.commit()
            callback(posts, error)
        }
        else {
            callback(null, error);
        }
    }
}

// TODO Remove later as this is temporary caching
val Context.cachedPosts: Array<Post> get() {
    val string = getSharedPreferences("threads", Context.MODE_PRIVATE).getString("threads", "[]")
    return gson.fromJson<Array<Post>>(string)!!
}
