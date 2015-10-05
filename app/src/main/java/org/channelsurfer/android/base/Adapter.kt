package org.channelsurfer.android.base

import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlin.properties.Delegates

public open class Adapter<T : Parcelable, U : ViewHolder<T, *>>(data: Array<T>) : RecyclerView.Adapter<U>() {
    var data: Array<T> by Delegates.observable(data) { prop, old, new -> if(old != new) notifyDataSetChanged() }

    open fun update(callback: (Exception?) -> Unit = {}) = callback(null)

    override fun getItemCount() = data.size()

    override fun onBindViewHolder(holder: U, position: Int) = holder.update(data[position])

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): U? = null
}
