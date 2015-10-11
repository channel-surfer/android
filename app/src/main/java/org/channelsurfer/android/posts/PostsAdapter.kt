package org.channelsurfer.android.posts

import android.content.Context
import android.view.ViewGroup
import org.channelsurfer.android.base.Adapter
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

public class PostsAdapter(
        private val context: Context,
        private val onClick: (Post) -> Unit = {}) : Adapter<Post, PostsItemView.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = PostsItemView.Holder(context) { onClick(it) }

    override fun update(callback: (Exception?) -> Unit) {
        async {
            context.fetchPosts("tech") { posts, error ->
                uiThread {
                    data = posts ?: data
                    callback(error)
                }
            }
        }
    }
}
