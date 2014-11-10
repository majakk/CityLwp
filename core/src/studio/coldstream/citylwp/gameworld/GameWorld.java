package studio.coldstream.citylwp.gameworld;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import studio.coldstream.citylwp.helpers.AssetLoader;

/**
 * Created by Majakk on 2014-11-09.
 */
public class GameWorld {
    public int items;

    Random myrand;

    public List<Sprite> fp;

    final double[] phase;
    final double[] phase2;
    final double[] ts;
    final int[] ypos;

    float cx, cy, scale;

    public GameWorld(){
        items = 75;

        myrand = new Random();
        myrand.setSeed(0);

        fp = new LinkedList<Sprite>();

        phase = new double[items];
        phase2 = new double[items];
        ts = new double[items];
        ypos = new int[items];

        for(int i = 0; i < items; i++){
            fp.add(i, new Sprite(AssetLoader.candy1));

            phase[i] = myrand.nextFloat() * 2 * Math.PI;
            phase2[i] = myrand.nextFloat() * 2 * Math.PI;
            ts[i] = myrand.nextFloat() * 1.8 + 0.2;
            ypos[i] = myrand.nextInt() % 400;
        }


    }

    public void update(float runTime) {
        ;
        //New positions here!
        for(int i = 0; i < items; i++){
            cx = 240 + 500.0f * (float)Math.sin(phase[i] + ts[i] * runTime / 12.25f);
            cy = 360 + 400.0f * (float)Math.sin(phase2[i] + ts[i] * runTime / 16.65f);

            fp.get(i).setPosition(cx,cy);

            scale = 1.5f + 0.5f * (float)ts[i] * (float)Math.cos(ts[i] * runTime / 14.35f);
            fp.get(i).setScale(scale * 0.4f);
            fp.get(i).setRotation(40.0f * (float)ts[i] * (float)Math.sin(ts[i] * runTime / 3.25f));

        }
    }

    public int getNumOfItems(){
        return items;
    }

}
