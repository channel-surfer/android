package org.channelsurfer.android.base

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.*
import org.json.JSONArray
import org.json.JSONObject

public class Network private constructor(private val queue: RequestQueue) {
    companion object {
        private var instance: Network? = null
        fun instance(context: Context): Network {
            instance = instance ?: Network(Volley.newRequestQueue(context.applicationContext))
            return instance!!
        }
    }

    fun image(url: String, maxWidth: Int = 0, maxHeight: Int = 0, scaleType: ImageView.ScaleType = ImageView.ScaleType.CENTER_INSIDE, decodeConfig: Bitmap.Config, callback: (Bitmap?, VolleyError?) -> Unit): ImageRequest {
        val listeners = Listeners(callback)
        val request = ImageRequest(url, listeners.listener, maxWidth, maxHeight, scaleType, decodeConfig, listeners.errorListener)
        queue.add(request)
        return request
    }

    fun jsonArray(method: Int = Request.Method.GET, url: String, body: String? = null, callback: (JSONArray?, VolleyError?) -> Unit): JsonArrayRequest {
        val listeners = Listeners(callback)
        val request = JsonArrayRequest(method, url, body, listeners.listener, listeners.errorListener)
        queue.add(request)
        return request
    }

    fun jsonObject(method: Int = Request.Method.GET, url: String, body: String? = null, callback: (JSONObject?, VolleyError?) -> Unit): JsonObjectRequest {
        val listeners = Listeners(callback)
        val request = JsonObjectRequest(method, url, body, listeners.listener, listeners.errorListener)
        queue.add(request)
        return request
    }

    fun string(method: Int = Request.Method.GET, url: String, callback: (String?, VolleyError?) -> Unit): StringRequest {
        val listeners = Listeners(callback)
        val request = StringRequest(method, url, listeners.listener, listeners.errorListener)
        queue.add(request)
        return request
    }

    private class Listeners<T>(callback: (T?, VolleyError?) -> Unit) {
        val listener: Response.Listener<T> = object : Response.Listener<T> {
            override fun onResponse(response: T?) = callback(response, null)
        }
        val errorListener: Response.ErrorListener = object : Response.ErrorListener {
            override fun onErrorResponse(error: VolleyError?) = callback(null, error)
        }
    }
}
