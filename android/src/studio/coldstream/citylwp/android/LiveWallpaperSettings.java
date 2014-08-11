package studio.coldstream.citylwp.android;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Scalar on 2014-08-11.
 */
public class LiveWallpaperSettings extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

}