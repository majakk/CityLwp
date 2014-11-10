package studio.coldstream.citylwp.android;


import android.util.Log;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidLiveWallpaperService;
import com.badlogic.gdx.backends.android.AndroidWallpaperListener;

import studio.coldstream.citylwp.LWPGame;
import studio.coldstream.citylwp.helpers.IOffsetsChanged;

public class LiveWallpaper extends AndroidLiveWallpaperService {
    public static float pixelOffset = 0;
    public static final String SHARED_PREFS_NAME = "citylwpsettings";

    @Override
    public void onCreateApplication () {
        super.onCreateApplication();

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        ApplicationListener listener = new MyLiveWallpaperListener();
        initialize(listener, config);
    }

    // implement AndroidWallpaperListener additionally to ApplicationListener
    // if you want to receive callbacks specific to live wallpapers
    public static class MyLiveWallpaperListener extends LWPGame implements AndroidWallpaperListener {
        @Override
        public void create() {
            super.resolver = new IOffsetsChanged() {
                @Override
                public float getxPixelOffset() {
                    return pixelOffset;
                }
            };

            super.create();
        };


        @Override
        public void offsetChange (float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset,
                                  int yPixelOffset) {
            Log.v("LiveWallpaper test", "offsetChange(xOffset:"+xOffset+" yOffset:"+yOffset+" xOffsetSteep:"+xOffsetStep+" yOffsetStep:"+yOffsetStep+" xPixelOffset:"+xPixelOffset+" yPixelOffset:"+yPixelOffset+")");
            //this.offsetChange(xOffset,yOffset,xOffsetStep,yOffsetStep,xPixelOffset,yPixelOffset);
            pixelOffset = xPixelOffset;
        }

        @Override
        public void previewStateChange (boolean isPreview) {
            Log.v("LiveWallpaper test", "previewStateChange(isPreview:"+isPreview+")");
        }
    }
}