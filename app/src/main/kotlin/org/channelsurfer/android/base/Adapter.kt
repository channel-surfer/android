package org.channelsurfer.android.base

import android.widget.BaseAdapter
import kotlin.properties.Delegates

public abstract class Adapter<T>(initial: Array<T>) : BaseAdapter() {
    var data: Array<T> by Delegates.observable(initial) { prop, old, new -> notifyDataSetChanged() }

    open fun update(callback: (Exception?) -> Unit = {}) = callback(null)

    override fun getCount() = data.size()
    override fun getItem(position: Int) = data[position]
    override fun getItemId(position: Int) = position.toLong()
}
