package org.channelsurfer.android.posts

import android.content.Context
import android.text.Html
import android.widget.RelativeLayout
import android.widget.TextView
import org.channelsurfer.android.base.CardView
import org.channelsurfer.android.base.ViewHolder
import org.channelsurfer.android.cardView
import org.channelsurfer.android.selectableItemBackground
import org.jetbrains.anko.*

public class PostsItemView(context: Context) : RelativeLayout(context) {
    companion object {
        private val PADDING = 4
    }
    private val cardView: CardView
    lateinit var bodyView: TextView private set

    init {
        horizontalPadding = context.dip(PADDING)
        verticalPadding = context.dip(PADDING / 2)
        cardView = cardView {
            foreground = context.selectableItemBackground
            bodyView = textView { padding = context.dip(PADDING * 2) }
            bodyView.layoutParams.width = matchParent
        }
        cardView.layoutParams.width = matchParent
    }

    class Holder(
            val view: PostsItemView,
            private val onClick: (Post) -> Unit) : ViewHolder<Post, PostsItemView>(view) {
        private lateinit var post: Post

        constructor(context: Context, onClick: (Post) -> Unit = {}) : this(PostsItemView(context), onClick)

        init { view.cardView.setOnClickListener { onClick(post) } }

        override fun update(post: Post) {
            this.post = post
            view.bodyView.text = Html.fromHtml(post.body)
        }
    }
}
