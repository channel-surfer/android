package org.channelsurfer.android.base

import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.io.Serializable

public open class SwipeRefreshRecyclerFragment<T : Serializable, U : ViewHolder<T, *>>(
        private val onUpdated: (Exception?) -> Unit = {}) : RecyclerFragment<T, U>() {
    lateinit var swipeView: SwipeRefreshLayout private set

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return view ?: run {
            swipeView = SwipeRefreshView(super.onCreateView(inflater, container, savedInstanceState))
            swipeView.setOnRefreshListener {
                adapter.update {
                    swipeView.isRefreshing = false
                    onUpdated(it)
                }
            }
            swipeView
        }
    }

    private inner class SwipeRefreshView(private val view: View) : SwipeRefreshLayout(activity) {
        init { addView(view) }
        override fun canChildScrollUp() = view.visibility == View.VISIBLE && ViewCompat.canScrollVertically(view, -1)
    }
}
