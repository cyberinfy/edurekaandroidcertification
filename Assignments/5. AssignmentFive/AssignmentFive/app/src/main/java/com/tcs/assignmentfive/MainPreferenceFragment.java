package com.tcs.assignmentfive;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;

public class MainPreferenceFragment extends PreferenceFragmentCompat {
@Override
public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        setPreferencesFromResource(R.xml.main_preferences, rootKey);
        }
}