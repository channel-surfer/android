package org.channelsurfer.android.base

import android.content.Context

public open class CardView(context: Context) : android.support.v7.widget.CardView(context) {
    init { useCompatPadding = true }
}
