package org.channelsurfer.android.posts

import android.content.Context
import android.text.TextUtils
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.dip
import org.jetbrains.anko.padding
import org.jetbrains.anko.singleLine
import org.jetbrains.anko.textView
import kotlin.properties.Delegates

public class PostsItemView(context: Context, post: Post) : LinearLayout(context) {
    val titleView: TextView
    var post: Post by Delegates.observable(post) { prop, old, new -> if(new != old) update() }

    init {
        orientation = LinearLayout.VERTICAL
        padding = context.dip(12)
        titleView = textView {
            textSize = 24f
            singleLine = true
            ellipsize = TextUtils.TruncateAt.END
        }
        update()
    }

    private fun update() {
        titleView.text = post.title
    }
}
