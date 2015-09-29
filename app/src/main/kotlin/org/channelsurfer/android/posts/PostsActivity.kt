package org.channelsurfer.android.posts

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.toast

public class PostsActivity() : AppCompatActivity() {
    lateinit var postsFragment: PostsFragment
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null) {
            postsFragment = PostsFragment()
            val adapter = PostsAdapter(this) { toast("Clicked ${it.id}") }
            fragmentManager.beginTransaction().add(android.R.id.content, postsFragment, PostsFragment.TAG).commit()
            postsFragment.adapter = adapter
        }
        else {
            postsFragment = fragmentManager.findFragmentByTag(PostsFragment.TAG) as PostsFragment
        }
    }
}
