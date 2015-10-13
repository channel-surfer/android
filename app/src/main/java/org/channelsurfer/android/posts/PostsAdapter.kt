package org.channelsurfer.android.posts

import android.content.Context
import android.view.ViewGroup
import org.channelsurfer.android.base.RecyclerAdapter
import org.channelsurfer.android.base.network
import org.channelsurfer.android.boards.Board
import org.channelsurfer.android.database.database
import org.channelsurfer.android.database.plusAssign
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

public class PostsAdapter(
        private val context: Context,
        private val onClick: (Post) -> Unit = {}) : RecyclerAdapter<Post, PostsItemView.Holder>() {
    companion object {
        private val board = Board("tech", "Technology")
    }
    private val network = context.network
    private val database = context.database

    init { data = database.posts }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = PostsItemView.Holder(context) { onClick(it) }

    override fun update(callback: (Exception?) -> Unit) {
        async {
            network.getPosts(board) { posts, error ->
                uiThread {
                    if(posts != null) {
                        database.posts = posts
                        data = posts
                    }
                    callback(error)
                }
            }
        }
    }
}
