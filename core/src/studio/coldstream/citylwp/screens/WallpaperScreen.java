package studio.coldstream.citylwp.screens;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

import studio.coldstream.citylwp.gameworld.GameRenderer;
import studio.coldstream.citylwp.gameworld.GameWorld;
import studio.coldstream.citylwp.helpers.IOffsetsChanged;

public class WallpaperScreen implements Screen {
    public Game game;
    public IOffsetsChanged resolver;
    public int sW, sH;
    public boolean isAndroid;

    private long diff, start;
    private final float targetFPS = 30f; // 20-30 is enough
    private final long targetDelay = 1000 / (long) targetFPS;

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;

    private OrthographicCamera mCamera;

    private boolean touched;
    private Vector3 touch;

    public WallpaperScreen(Game game, IOffsetsChanged resolver) {
        this.game = game;
        this.resolver = resolver;
        this.sW = Gdx.graphics.getWidth();
        this.sH = Gdx.graphics.getHeight();
        this.isAndroid = (Gdx.app.getType() == ApplicationType.Android);

        mCamera = new OrthographicCamera();

        world = new GameWorld();
        renderer = new GameRenderer(this.world, this.mCamera, this.sH, this.sW);
    }

    public void render(float delta) {
        if (Gdx.input.isTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            mCamera.unproject(touch);
            touched = true;
        } else {
            touched = false;
        }

        Gdx.gl20.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl20.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glViewport(0, 0, sW, sH);

        if (resolver != null)
            mCamera.position.x = (Gdx.graphics.getWidth() / 2) - resolver.getxPixelOffset();

        mCamera.update();

        runTime += delta;
        world.update(runTime);
        renderer.render(runTime);
    }

    public void pause() {}
    public void resume() {}

    public void show() {
        Gdx.app.log("WallpaperScreen", "show called");
        touch = new Vector3();
        resetCamera();
    }

    public void resize(int width, int height) {
        this.sW = Gdx.graphics.getWidth();
        this.sH = Gdx.graphics.getHeight();
    }

    public void hide() {}
    public void dispose() {}

    public void limitFPS() {
        diff = System.currentTimeMillis() - start;

        if (diff < targetDelay) {
            try {
                Thread.sleep(targetDelay - diff);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        start = System.currentTimeMillis();
    }

    private void resetCamera() {
        mCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mCamera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);

        /*mCamera = new OrthographicCamera(sW, sH);
        mCamera.setToOrtho(false, sW, sH);
        mCamera.position.set(sW / 2, sH / 2, 0);*/
    }
    /*public void offsetsChanged(float xOffset, float yOffset, float xOffsetStep,
                               float yOffsetStep, int xPixelOffset, int yPixelOffset) {
        if(mCamera != null && SCROLL){
            mCamera.translate( ((480 * (xOffset * 0.5f)) + 120) , (int)(mCamera.viewportHeight / 2) );
            //formel: mCamera.setCenter(( (Camera-WIDTH * (screensCount-1)) * xOffset ) - (Camera-WIDTH / 2) ,mCamera.getCenterY() );
        }
    }*/
}
