package org.channelsurfer.android.base

import android.content.Context
import android.util.TypedValue

public open class CardView(context: Context) : android.support.v7.widget.CardView(context) {
    init {
        val color = TypedValue()
        useCompatPadding = true
        context.theme.resolveAttribute(android.support.v7.cardview.R.attr.cardBackgroundColor, color, true)
        setCardBackgroundColor(color.data)
    }
}
