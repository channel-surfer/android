package org.channelsurfer.android

import android.os.Parcel
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

val Date.unixTime: Int get() = (time / 1000).toInt()

fun JSONArray.toObjectArray() = (1..length()).map { getJSONObject(it - 1) }

fun JSONObject.getNullableString(key: String): String? = if(has(key)) getString(key) else null

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
