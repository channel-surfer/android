package org.channelsurfer.android.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

val Context.darkTheme: Boolean get() = sharedPreferences.getBoolean("dark_theme", false)

val Context.sharedPreferences: SharedPreferences get() = PreferenceManager.getDefaultSharedPreferences(this)
