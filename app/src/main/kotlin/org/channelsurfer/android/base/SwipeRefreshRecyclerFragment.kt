package org.channelsurfer.android.base

import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout

public open class SwipeRefreshRecyclerFragment<T, U : ViewHolder<T, *>>(
        data: Array<T>,
        private val onUpdated: (Exception?) -> Unit = {}) : RecyclerFragment<T, U>(data) {
    lateinit var swipeView: SwipeRefreshLayout private set

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = super.onCreateView(inflater, container, savedInstanceState) as RelativeLayout
        view.removeView(recyclerView)
        swipeView = SwipeView()
        view.addView(swipeView)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swipeView.setOnRefreshListener {
            adapter.update {
                swipeView.isRefreshing = false
                onUpdated(it)
            }
        }
    }

    private inner class SwipeView() : SwipeRefreshLayout(activity) {
        init { addView(recyclerView) }

        override fun canChildScrollUp(): Boolean {
            return recyclerView.visibility == View.VISIBLE && ViewCompat.canScrollVertically(recyclerView, -1)
        }
    }
}
