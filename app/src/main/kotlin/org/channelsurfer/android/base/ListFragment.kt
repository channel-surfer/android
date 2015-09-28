package org.channelsurfer.android.base

import android.os.Bundle

public open class ListFragment<T> : android.app.ListFragment() {
    private var activityCreated = false
    private var callbacks: List<(T) -> Unit> = emptyList()

    var listAdapter: Adapter<T>
        get() = super.getListAdapter() as Adapter<T>
        set(value) = super.setListAdapter(value)

    fun setOnItemClickListener(callback: (T) -> Unit) {
        if(activityCreated) {
            listView.setOnItemClickListener { adapterView, view, i, l -> callback(listAdapter.getItem(i)) }
        }
        else {
            callbacks += callback
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activityCreated = true
        callbacks.forEach { setOnItemClickListener(it) }
        callbacks = emptyList()
    }
}
