package org.channelsurfer.android.base

import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

public open class SwipeRefreshListFragment<T>() : ListFragment<T>() {
    lateinit var swipeView: SwipeRefreshLayout private set

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        swipeView = SwipeView(this)
        swipeView.addView(view)
        return swipeView
    }

    private class SwipeView<T>(val fragment: SwipeRefreshListFragment<T>) : SwipeRefreshLayout(fragment.activity) {
        override fun canChildScrollUp(): Boolean {
            return fragment.listView.visibility == View.VISIBLE && ViewCompat.canScrollVertically(fragment.listView, -1)
        }
    }
}
