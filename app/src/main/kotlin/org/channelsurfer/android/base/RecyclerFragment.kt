package org.channelsurfer.android.base

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout

public open class RecyclerFragment<T, U : ViewHolder<T, *>>(data: Array<T>) : Fragment() {
    lateinit var recyclerView: RecyclerView protected set
    var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        get() = if(activityCreated) recyclerView.layoutManager else $layoutManager
        set(value) = if(activityCreated) recyclerView.layoutManager = value else $layoutManager = value
    open var adapter = Adapter<T, U>(data)
        get() = if(activityCreated) recyclerView.adapter as Adapter<T, U> else $adapter
        set(value) = if(activityCreated) recyclerView.adapter = value else $adapter = value

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = RelativeLayout(activity)
        recyclerView = RecyclerView(activity)
        view.addView(recyclerView)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.adapter = $adapter
        recyclerView.layoutManager = $layoutManager
    }
}
