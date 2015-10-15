package org.channelsurfer.android.base

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import kotlin.properties.Delegates

public open class RecyclerAdapter<T, U : ViewHolder<T, *>> : RecyclerView.Adapter<U>() {
    var data: List<T> by Delegates.observable(emptyList()) { prop, old, new -> if(old != new) notifyDataSetChanged() }

    open fun initialize() {}
    open fun update(callback: (Exception?) -> Unit = {}) = callback(null)
    override fun getItemCount() = data.size()
    override fun onBindViewHolder(holder: U, position: Int) = holder.update(data[position])
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): U? = null
}
