package org.channelsurfer.android.base

import android.graphics.Bitmap
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.*

public class Network(private val queue: RequestQueue) {
    fun image(
            url: String,
            maxWidth: Int = 0,
            maxHeight: Int = 0,
            scaleType: ImageView.ScaleType = ImageView.ScaleType.CENTER_INSIDE,
            decodeConfig: Bitmap.Config,
            callback: (Bitmap?, VolleyError?) -> Unit): ImageRequest {
        val listeners = Listeners(callback)
        val request = ImageRequest(
                url, listeners.listener,
                maxWidth, maxHeight, scaleType, decodeConfig, listeners.errorListener)
        queue.add(request)
        return request
    }

    fun string(
            method: Int = Request.Method.GET,
            url: String,
            body: String? = null,
            callback: (String?, VolleyError?) -> Unit): StringRequest {
        val listeners = Listeners(callback)
        val request = object : StringRequest(method, url, listeners.listener, listeners.errorListener) {
            override fun getBody() = body?.toByteArray()
        }
        queue.add(request)
        return request
    }

    private class Listeners<T : Any>(callback: (T?, VolleyError?) -> Unit) {
        val listener: Response.Listener<T> = object : Response.Listener<T> {
            override fun onResponse(response: T?) = callback(response, null)
        }
        val errorListener: Response.ErrorListener = object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) = callback(null, error)
        }
    }
}
