package org.channelsurfer.android.base

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import org.channelsurfer.android.R
import org.channelsurfer.android.preferences.PreferencesActivity
import org.channelsurfer.android.preferences.darkTheme

public open class Activity : AppCompatActivity() {
    private var themeSet = false

    open fun themeChanged(resid: Int) = themeResId != resid

    override fun setTheme(resid: Int) {
        super.setTheme(resid)
        themeSet = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if(!themeSet) setTheme(if(darkTheme) R.style.app_dark else R.style.app_light)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if(this !is PreferencesActivity) {
            menu.add(R.string.preference_title).setOnMenuItemClickListener {
                startActivity<PreferencesActivity>()
                true
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        val resid = data.getIntExtra("resid", themeResId)
        if(resultCode == Int.MIN_VALUE && themeChanged(resid)) recreate()
    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("resid", themeResId)
        setResult(Int.MIN_VALUE, intent)
        super.onBackPressed()
    }
}
