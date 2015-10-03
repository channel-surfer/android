package org.channelsurfer.android.preferences

import android.os.Bundle
import android.preference.PreferenceFragment
import org.channelsurfer.android.R

public class PreferencesFragment : PreferenceFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)
    }
}
