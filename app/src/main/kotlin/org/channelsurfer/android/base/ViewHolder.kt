package org.channelsurfer.android.base

import android.support.v7.widget.RecyclerView
import android.view.View

public abstract class ViewHolder<T, U : View>(val view: U) : RecyclerView.ViewHolder(view) {
    abstract fun update(data: T)
}
