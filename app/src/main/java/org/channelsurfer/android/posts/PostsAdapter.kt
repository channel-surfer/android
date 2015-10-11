package org.channelsurfer.android.posts

import android.content.Context
import android.view.ViewGroup
import com.github.salomonbrys.kotson.fromJson
import org.channelsurfer.android.base.Adapter
import org.channelsurfer.android.base.network
import org.channelsurfer.android.base.gson
import org.channelsurfer.android.boards.Board
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

public class PostsAdapter(
        private val context: Context,
        private val onClick: (Post) -> Unit = {}) : Adapter<Post, PostsItemView.Holder>() {
    companion object {
        private val board = Board("tech", "Technology")
    }

    init {
        // TODO Remove/change later as this is temporary caching
        data = run {
            val string = context.getSharedPreferences("threads", Context.MODE_PRIVATE).getString("threads", "[]")
            gson.fromJson<List<Post>>(string)!!
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = PostsItemView.Holder(context) { onClick(it) }

    override fun update(callback: (Exception?) -> Unit) {
        async {
            context.network.fetchPosts(board) { posts, error ->
                uiThread {
                    // TODO Remove/change later as this is temporary caching
                    if(posts != null) {
                        val editor = context.getSharedPreferences("threads", Context.MODE_PRIVATE).edit()
                        editor.putString("threads", gson.toJson(posts).toString())
                        editor.commit()
                        data = posts
                    }
                    callback(error)
                }
            }
        }
    }
}
