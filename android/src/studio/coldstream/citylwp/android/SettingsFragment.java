package studio.coldstream.citylwp.android;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Scalar on 2014-08-12.
 */
public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.wallpaper_settings);
    }

}