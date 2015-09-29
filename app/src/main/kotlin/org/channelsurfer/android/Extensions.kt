package org.channelsurfer.android

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.view.ViewManager
import org.channelsurfer.android.base.CardView
import org.jetbrains.anko.custom.ankoView
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

val Date.unixTime: Int get() = (time / 1000).toInt()

val Context.selectableItemBackground: Drawable get() {
    val typedArray = obtainStyledAttributes(intArrayOf(android.R.attr.selectableItemBackground))
    val selectableItemBackground = typedArray.getDrawable(0)
    typedArray.recycle()
    return selectableItemBackground
}

var Parcel.int: Int
    get() = readInt()
    set(value) = writeInt(value)

var Parcel.string: String
    get() = readString()
    set(value) = writeString(value)

var Parcel.nullableString: String?
    get() = if(int == 1) string else null
    set(value) {
        int = if(value != null) 1 else 0
        if(value != null) string = value
    }

fun JSONArray.toObjectArray() = (1..length()).map { getJSONObject(it - 1) }

fun JSONObject.getNullableString(key: String): String? = if(has(key)) getString(key) else null

fun ViewManager.cardView(init: CardView.() -> Unit = {}) = ankoView({ CardView(it) }, init)
