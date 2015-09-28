package org.channelsurfer.android.posts

import android.content.Context
import android.view.View
import android.view.ViewGroup
import org.channelsurfer.android.base.Adapter
import org.channelsurfer.android.base.Network
import org.channelsurfer.android.toObjectArray
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

public class PostsAdapter(private val context: Context, posts: Array<Post> = arrayOf()) : Adapter<Post>(posts) {
    private val network = Network.instance(context)

    override fun update(callback: (Exception?) -> Unit) {
        async {
            network.jsonArray(url="https://8ch.net/tech/catalog.json") { response, error ->
                if(response != null && error == null) {
                    val threads = response
                            .toObjectArray()
                            .flatMap { it.getJSONArray("threads").toObjectArray() }
                            .map { Post.fromJSON(it) }
                            .toTypedArray()
                    uiThread { data = threads }
                }
                uiThread { callback(error) }
            }
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val itemView = convertView as? PostsItemView
        val post = getItem(position)
        itemView?.post = post
        return itemView ?: PostsItemView(context, post)
    }
}
