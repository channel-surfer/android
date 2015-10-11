package org.channelsurfer.android.posts

import android.content.Context
import android.graphics.Typeface
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.mikepenz.iconics.view.IconicsTextView
import org.channelsurfer.android.R
import org.channelsurfer.android.base.*
import org.jetbrains.anko.*

public class PostsItemView(context: Context) : CardView(context) {
    companion object {
        private val PADDING = 4
    }
    lateinit var body: TextView private set
    lateinit var title: TextView private set
    lateinit var sticky: IconicsTextView private set
    lateinit var locked: IconicsTextView private set
    lateinit var header: TextView private set
    lateinit var date: TextView private set
    lateinit var replies: TextView private set

    init {
        val headerBackground = TypedValue()
        context.theme.resolveAttribute(R.attr.postHeaderBackgroundColor, headerBackground, true)

        foreground = context.selectableItemBackground
        layoutParams = run {
            val layout = ViewGroup.MarginLayoutParams(matchParent, wrapContent)
            layout.horizontalMargin = context.dip(PADDING)
            layout.verticalMargin = context.dip(PADDING / 2)
            layout
        }

        verticalLayout {
            verticalLayout {
                orientation = LinearLayout.HORIZONTAL
                backgroundColor = headerBackground.data

                header = textView {
                    singleLine = true
                    ellipsize = TextUtils.TruncateAt.END
                    gravity = Gravity.CENTER
                    horizontalPadding = context.dip(PADDING)
                }.lparams { weight = 1f }

                sticky = iconicsTextView {
                    text = "{cmd-pin}"
                    gravity = Gravity.CENTER
                }

                locked = iconicsTextView {
                    text = "{gmd-lock}"
                    gravity = Gravity.CENTER
                }
            }

            title = textView {
                gravity = Gravity.CENTER
                topPadding = context.dip(PADDING * 2)
                horizontalPadding = topPadding
                textSizeDimen = R.dimen.abc_text_size_subhead_material
                setTypeface(null, Typeface.BOLD)
            }.lparams(width=wrapContent)

            body = textView {
                padding = context.dip(PADDING * 2)
                maxLines = 12
                ellipsize = TextUtils.TruncateAt.END
            }

            view {
                backgroundColor = headerBackground.data
            }.lparams(height = context.dip(1))

            verticalLayout {
                orientation = LinearLayout.HORIZONTAL

                date = textView {
                    horizontalPadding = context.dip(PADDING)
                }.lparams { weight = 1f }

                replies = textView {
                    rightPadding = context.dip(PADDING)
                }
            }
        }
    }

    class Holder(
            private val context: Context,
            private val onClick: (Post) -> Unit = {}) : ViewHolder<Post, PostsItemView>(PostsItemView(context)) {
        override fun update(data: Post) {
            view.header.text = data.header
            view.body.text = data.body
            view.title.text = data.title
            view.date.text = context.prettyFormat(data.createdAt)
            view.replies.text = context.resources.getQuantityString(R.plurals.replies, data.replies, data.replies)

            view.title.visibility = if(data.title != null) View.VISIBLE else View.GONE
            view.sticky.visibility = if(data.isSticky) View.VISIBLE else View.GONE
            view.locked.visibility = if(data.isLocked) View.VISIBLE else View.GONE
            view.replies.visibility = if(data.replies > 0) View.VISIBLE else View.GONE

            view.setOnClickListener { onClick(data) }
        }
    }
}
