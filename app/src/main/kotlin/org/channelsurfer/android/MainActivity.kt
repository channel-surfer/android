package org.channelsurfer.android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.jetbrains.anko.*

final public class MainActivity : AppCompatActivity() {
    lateinit var helloTextView: TextView private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        relativeLayout {
            horizontalPadding = dimen(R.dimen.activity_horizontal_margin)
            verticalPadding = dimen(R.dimen.activity_vertical_margin)
            helloTextView = textView(R.string.hello_world)
        }
    }
}
