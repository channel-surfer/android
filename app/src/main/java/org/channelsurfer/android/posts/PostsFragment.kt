package org.channelsurfer.android.posts

import org.channelsurfer.android.base.SwipeRefreshRecyclerFragment

public class PostsFragment(onUpdated: (Exception?) -> Unit = {}) :
        SwipeRefreshRecyclerFragment<Post, PostsItemView.Holder>(onUpdated) {
    companion object {
        val TAG = "PostsFragment"
    }
}
