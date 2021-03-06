package org.channelsurfer.android.base

import android.app.Fragment
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import org.jetbrains.anko.dip
import org.jetbrains.anko.verticalPadding
import java.util.*

public open class RecyclerFragment<T, U : ViewHolder<T, *>> : Fragment() {
    companion object {
        val TAG = "SwipeRefreshRecyclerFragment"
    }

    private var defaultLayoutManager: RecyclerView.LayoutManager? = null
    lateinit var recyclerView: RecyclerView private set
    @Suppress("UNCHECKED_CAST")
    var adapter: Adapter<T, U> = Adapter()
        get() = if(view != null) recyclerView.adapter as Adapter<T, U> else field
        set(value) = if(view != null) recyclerView.adapter = value else field = value
    var layoutManager: RecyclerView.LayoutManager
        get() = if(view != null) recyclerView.layoutManager else {
            defaultLayoutManager = defaultLayoutManager ?: LinearLayoutManager(activity)
            defaultLayoutManager!!
        }
        set(value) = if(view != null) recyclerView.layoutManager = value else defaultLayoutManager = value

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?) = view ?: run {
        recyclerView = RecyclerView(activity)
        recyclerView.clipToPadding = false
        recyclerView.verticalPadding = dip(8)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        defaultLayoutManager = null
        recyclerView
    }

    @Suppress("UNCHECKED_CAST")
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if(savedInstanceState != null) {
            val state = savedInstanceState.getParcelable<Parcelable>("$TAG.STATE")
            val data = (savedInstanceState.getSerializable("$TAG.DATA") as ArrayList<T>).toList()
            recyclerView.layoutManager.onRestoreInstanceState(state)
            adapter.data = data
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("$TAG.STATE", recyclerView.layoutManager.onSaveInstanceState())
        outState.putSerializable("$TAG.DATA", adapter.data.toArrayList())
    }
}
