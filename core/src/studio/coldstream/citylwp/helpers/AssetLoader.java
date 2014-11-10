package studio.coldstream.citylwp.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Majakk on 2014-11-08.
 */
public class AssetLoader {
    public static Texture texture;
    public static TextureRegion candy1, candy2;

    public static void load() {

        texture = new Texture(Gdx.files.internal("gfx/candy.png"));

        candy1 = new TextureRegion(texture, 1, 1, 43, 43);
        candy1.flip(false, true);

        candy2 = new TextureRegion(texture, 44, 0, 82, 82);
        candy2.flip(false, true);

    }

    public static void dispose() {
        texture.dispose();
    }

}
