package org.channelsurfer.android.posts

import android.os.Parcel
import android.os.Parcelable
import org.channelsurfer.android.getNullableString
import org.channelsurfer.android.int
import org.channelsurfer.android.nullableString
import org.channelsurfer.android.string
import org.json.JSONObject
import java.util.*

open public data class Post(
        private val no: Int,
        private val com: String,
        private val sub: String?,
        private val time: Int,
        val replies: Int,
        private val sticky: Int,
        private val locked: Int,
        private val lastModified: Int) : Parcelable {
    companion object {
        public val CREATOR: Parcelable.Creator<Post> = object : Parcelable.Creator<Post> {
            override fun createFromParcel(parcelIn: Parcel) = Post(
                    no=parcelIn.int,
                    com=parcelIn.string,
                    sub=parcelIn.nullableString,
                    time=parcelIn.int,
                    replies=parcelIn.int,
                    sticky=parcelIn.int,
                    locked=parcelIn.int,
                    lastModified = parcelIn.int)
            override fun newArray(size: Int) = arrayOfNulls<Post>(size)
        }
        fun fromJSON(json: JSONObject) = Post(
                no=json.getInt("no"),
                com=json.getString("com"),
                sub=json.getNullableString("sub"),
                time=json.getInt("time"),
                replies=json.getInt("replies"),
                sticky=json.getInt("sticky"),
                locked=json.getInt("locked"),
                lastModified=json.getInt("last_modified"))
    }

    val id = no
    val body = com
    val isSticky = sticky == 1
    val isLocked = locked == 1
    val createdAt by lazy { Date(time.toLong() * 1000) }
    val updatedAt by lazy { Date(lastModified.toLong() * 1000) }
    val title by lazy { if(sub != null) "$no - $sub" else "$no" }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.int = no
        dest.string = com
        dest.nullableString = sub
        dest.int = time
        dest.int = replies
        dest.int = sticky
        dest.int = locked
        dest.int = lastModified
    }
}
