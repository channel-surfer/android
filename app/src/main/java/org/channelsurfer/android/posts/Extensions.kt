package org.channelsurfer.android.posts

import android.content.Context
import org.channelsurfer.android.base.getNullableString
import org.channelsurfer.android.base.globalNetwork
import org.channelsurfer.android.base.objectList
import org.json.JSONArray
import org.json.JSONObject

// TODO Change later to be a Network extension instead of Context extension
fun Context.fetchPosts(callback: (Array<Post>?, Exception?) -> Unit) {
    globalNetwork.jsonArray(url="https://8ch.net/tech/catalog.json") { response, error ->
        val posts = response
                ?.objectList
                ?.flatMap { it.getJSONArray("threads").objectList.map { it.post } }
                ?.toTypedArray()
        // TODO Remove later as this is temporary caching. This also means context is no longer directly needed
        if(error == null) {
            val editor = getSharedPreferences("threads", Context.MODE_PRIVATE).edit()
            editor.putString("threads", response.toString())
            editor.commit()
        }
        callback(posts, error)
    }
}

val JSONObject.post: Post get() = Post(
        no=getInt("no"),
        com=getString("com"),
        email=getNullableString("email"),
        name=getString("name"),
        capcode=getNullableString("capcode"),
        trip=getNullableString("trip"),
        sub=getNullableString("sub"),
        time=getInt("time"),
        replies=getInt("replies"),
        sticky=getInt("sticky"),
        locked=getInt("locked"),
        lastModified=getInt("last_modified"))

// TODO Remove later as this is temporary caching
val Context.cachedPosts: Array<Post> get() {
    val string = getSharedPreferences("threads", Context.MODE_PRIVATE).getString("threads", "[]")
    return JSONArray(string)
            .objectList
            .flatMap { it.getJSONArray("threads").objectList.map { it.post } }
            .toTypedArray()
}
