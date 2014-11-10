package studio.coldstream.citylwp.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import studio.coldstream.citylwp.helpers.AssetLoader;

/**
 * Created by Majakk on 2014-11-09.
 */
public class GameRenderer {
    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;
    private Sprite gemSprite;

    private int gameWidth;
    private int gameHeight;

    private TextureRegion candy1, candy2;

    public GameRenderer(GameWorld world, OrthographicCamera inCam, int gameHeight, int gameWidth) {
        myWorld = world;

        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;

        cam = inCam;
        cam.setToOrtho(true, gameWidth, gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        initAssets();

        gemSprite = new Sprite(AssetLoader.candy1);


    }

    private void initAssets() {
        candy1 = AssetLoader.candy1;
        candy2 = AssetLoader.candy2;
    }

    public void render(float runTime) {

        setBackGround();

        batcher.begin();

        //batcher.disableBlending();

        for(int i = 0; i < myWorld.getNumOfItems(); i++) {

            //batch.draw(img, img.getWidth() * i, (int)(img.getHeight() * (1 + Math.cos(elapsedTime))));
            //gemSprite.setPosition(cam.position.x - candy1.getRegionWidth() * i, (float) (candy1.getRegionHeight() * (1 + Math.cos(runTime))));
            //gemSprite.rotate((float) elapsedTime);
            //gemSprite.setScale(2 * Math.abs((float)Math.cos(runTime)));
            //gemSprite.draw(batcher);
            myWorld.fp.get(i).draw(batcher);
        }

        batcher.end();
    }

    private void setBackGround(){
        Gdx.gl.glClearColor(0.5f, 0.6f, 0.7f, 1);
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
    }
}
