package org.channelsurfer.android.posts

import android.os.Parcel
import android.os.Parcelable
import org.channelsurfer.android.base.fromHtml
import org.channelsurfer.android.base.int
import org.channelsurfer.android.base.nullableString
import org.channelsurfer.android.base.string
import java.util.*

public data class Post(
        private val no: Int,
        private val com: String,
        private val email: String?,
        private val name: String,
        private val capcode: String?,
        private val trip: String?,
        private val sub: String?,
        private val time: Int,
        val replies: Int,
        private val sticky: Int,
        private val locked: Int,
        private val lastModified: Int) : Parcelable {
    companion object {
        val CREATOR: Parcelable.Creator<Post> = object : Parcelable.Creator<Post> {
            override fun createFromParcel(parcelIn: Parcel) = Post(
                    no=parcelIn.int,
                    com=parcelIn.string,
                    email=parcelIn.nullableString,
                    name=parcelIn.string,
                    capcode=parcelIn.nullableString,
                    trip=parcelIn.nullableString,
                    sub=parcelIn.nullableString,
                    time=parcelIn.int,
                    replies=parcelIn.int,
                    sticky=parcelIn.int,
                    locked=parcelIn.int,
                    lastModified = parcelIn.int)
            override fun newArray(size: Int) = arrayOfNulls<Post>(size)
        }
    }

    val id by lazy { no }
    val title by lazy { sub?.fromHtml }
    val isSticky by lazy { sticky == 1 }
    val isLocked by lazy { locked == 1 }
    val createdAt by lazy { Date(time.toLong() * 1000) }
    val updatedAt by lazy { Date(lastModified.toLong() * 1000) }
    val body by lazy { com.fromHtml }
    val header by lazy {
        var header = name
        if(trip != null) header += trip
        if(email != null) header = "<a href=\"mailto:$email\">$header</a>"
        if(capcode != null) header += " ## $capcode"
        header = "<b>$id</b> - $header"
        header.fromHtml
    }

    private constructor() : this(0, "", null, "", null, null, null, 0, 0, 0, 0, 0)

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.int = no
        dest.string = com
        dest.nullableString = email
        dest.string = name
        dest.nullableString = capcode
        dest.nullableString = trip
        dest.nullableString = sub
        dest.int = time
        dest.int = replies
        dest.int = sticky
        dest.int = locked
        dest.int = lastModified
    }
}
