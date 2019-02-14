package com.media.dmitry68.vacationrecords.settings;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.media.dmitry68.vacationrecords.R;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    private FragmentManager fragmentManager;

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        setPreferencesFromResource(R.xml.preferences, s);
        fragmentManager = getFragmentManager();
        Preference actionPreference = findPreference("button_action_category_key");
        actionPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                fragmentManager.beginTransaction()
                        .add(android.R.id.content, new SettingsActionFragment())
                        .addToBackStack(SettingsActionFragment.SETTINGS_ACTION_FRAGMENT_TAG)
                        .commit();
                return true;
            }
        });
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
}
