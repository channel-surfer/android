package org.channelsurfer.android.base

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Html
import android.view.ViewManager
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.mikepenz.iconics.view.IconicsTextView
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.internals.AnkoInternals
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

private var globalRequestQueue: RequestQueue? = null

val Date.unixTime: Int get() = (time / 1000).toInt()

val Context.selectableItemBackground: Drawable get() {
    val typedArray = obtainStyledAttributes(intArrayOf(android.R.attr.selectableItemBackground))
    val selectableItemBackground = typedArray.getDrawable(0)
    typedArray.recycle()
    return selectableItemBackground
}

val Context.globalNetwork: Network get() {
    globalRequestQueue = globalRequestQueue ?: Volley.newRequestQueue(applicationContext)
    return Network(globalRequestQueue!!)
}

val Context.themeResId: Int get() = javaClass.getMethod("getThemeResId").invoke(this) as Int

val String.fromHtml: CharSequence get() = with(Html.fromHtml(this)) {
    var start = 0
    var end = length()
    while(start < end && Character.isWhitespace(charAt(start))) start++
    while(end > start && Character.isWhitespace(charAt(end - 1))) end--
    return subSequence(start, end)
}

inline fun <reified T : Activity> Activity.startActivity(vararg params: Pair<String, Any>) {
    AnkoInternals.internalStartActivityForResult(this, T::class.java, 0, params)
}

fun Context.prettyFormat(date: Date) = PrettyTime(resources.configuration.locale).format(date)

fun ViewManager.iconicsTextView(init: IconicsTextView.() -> Unit = {}) = ankoView({ IconicsTextView(it) }, init)
