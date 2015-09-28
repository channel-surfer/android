package org.channelsurfer.android.posts

import android.os.Bundle
import org.channelsurfer.android.base.SwipeRefreshListFragment

public class PostsFragment: SwipeRefreshListFragment<Post>() {
    companion object {
        val TAG = "PostsFragment"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swipeView.setOnRefreshListener { listAdapter.update { swipeView.isRefreshing = false } }
    }
}
