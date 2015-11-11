package org.channelsurfer.android.posts

import android.os.Bundle
import android.os.SystemClock
import org.channelsurfer.android.base.Activity
import org.channelsurfer.android.base.prettyFormat
import org.channelsurfer.android.database.all
import org.channelsurfer.android.database.database
import org.jetbrains.anko.async
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

public class PostsActivity() : Activity() {
    private var isRunning = false
    private lateinit var postsFragment: PostsFragment
    private lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState == null) {
            postsFragment = PostsFragment()
            fragmentManager.beginTransaction().add(android.R.id.content, postsFragment, PostsFragment.TAG).commit()
        }
        else {
            postsFragment = fragmentManager.findFragmentByTag(PostsFragment.TAG) as PostsFragment
        }

        postsAdapter = PostsAdapter(this, database.boards.all[0]) { toast("Clicked ${it.id}") }
        postsFragment.adapter = postsAdapter
    }

    override fun onResume() {
        super.onResume()
        isRunning = true
        async { while(isRunning) {
            uiThread { supportActionBar.subtitle = prettyFormat(postsAdapter.board.updatedAt) }
            SystemClock.sleep(1000)
        }}
    }

    override fun onPause() {
        isRunning = false
        super.onPause()
    }
}
