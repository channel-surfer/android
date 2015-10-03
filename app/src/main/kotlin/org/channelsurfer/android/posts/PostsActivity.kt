package org.channelsurfer.android.posts

import android.os.Bundle
import org.channelsurfer.android.base.Activity
import org.jetbrains.anko.toast

public class PostsActivity() : Activity() {
    lateinit var postsFragment: PostsFragment private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState == null) {
            postsFragment = PostsFragment()
            postsFragment.adapter = PostsAdapter(this, data=cachedPosts) { toast("Clicked ${it.id}") }
            fragmentManager.beginTransaction().add(android.R.id.content, postsFragment, PostsFragment.TAG).commit()
        }
        else {
            postsFragment = fragmentManager.findFragmentByTag(PostsFragment.TAG) as PostsFragment
            postsFragment.adapter = PostsAdapter(this) { toast("Clicked ${it.id}") }
        }
    }
}
