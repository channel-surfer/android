package org.channelsurfer.android.posts

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

public class PostsActivity() : AppCompatActivity() {
    val postsFragment by lazy { fragmentManager.findFragmentByTag(PostsFragment.TAG) as PostsFragment }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null) {
            val fragment = PostsFragment()
            fragmentManager.beginTransaction().add(android.R.id.content, fragment, PostsFragment.TAG).commit()
            fragment.listAdapter = PostsAdapter(this)
            fragment.setOnItemClickListener { println(it) }
        }
    }
}
