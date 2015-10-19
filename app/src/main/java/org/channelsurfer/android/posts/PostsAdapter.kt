package org.channelsurfer.android.posts

import android.content.Context
import android.view.ViewGroup
import org.channelsurfer.android.base.RecyclerAdapter
import org.channelsurfer.android.base.network
import org.channelsurfer.android.base.unixTime
import org.channelsurfer.android.boards.Board
import org.channelsurfer.android.database.database
import org.channelsurfer.android.database.plusAssign
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import java.util.*

public class PostsAdapter(
        private val context: Context,
        board: Board,
        private val onClick: (Post) -> Unit = {}) : RecyclerAdapter<Post, PostsItemView.Holder>() {
    private val network = context.network
    private val database = context.database
    var board = board
        private set

    override fun initialize() { data = database.posts[board] }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = PostsItemView.Holder(context) { onClick(it) }

    override fun update(callback: (Exception?) -> Unit) { async { network.getPosts(board) { posts, error ->
        if(error == null) {
            board = board.copy(lastModified = Date().unixTime)
            database.boards += board
        }
        if(posts != null) database.posts[board] = posts
        uiThread {
            if(posts != null) data = posts
            callback(error)
        }
    }}}
}
