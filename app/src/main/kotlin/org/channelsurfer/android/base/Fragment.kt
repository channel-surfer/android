package org.channelsurfer.android.base

import android.os.Bundle

public open class Fragment : android.app.Fragment() {
    var activityCreated = false
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activityCreated = true
    }
}
