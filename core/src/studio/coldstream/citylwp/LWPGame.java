package studio.coldstream.citylwp;

import com.badlogic.gdx.Game;

import studio.coldstream.citylwp.helpers.AssetLoader;
import studio.coldstream.citylwp.helpers.IOffsetsChanged;
import studio.coldstream.citylwp.screens.WallpaperScreen;

/**
 * Created by Scalar on 2014-08-27.
 */
public class LWPGame extends Game {
    public IOffsetsChanged resolver = null;

    @Override
    public void create() {
        AssetLoader.load();
        setScreen(new WallpaperScreen(this, resolver));
    }

    @Override
    public void dispose () {
        super.dispose();
        resolver = null;
        getScreen().dispose();
        AssetLoader.dispose();
    }
}