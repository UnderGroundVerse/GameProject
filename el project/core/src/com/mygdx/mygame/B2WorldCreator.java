package com.mygdx.mygame;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

public class B2WorldCreator
{
    Rectangle rect;
    public B2WorldCreator(Map map)
    {
        super();
        rect = new Rectangle();

        // Ground
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){

            rect =  ( (RectangleMapObject) object).getRectangle();
            new Ground(rect);
        }

        // Pipes
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){

            rect =  ( (RectangleMapObject) object).getRectangle();
            new Pipe(rect);
        }

        // Coins
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){

            rect =  ( (RectangleMapObject) object).getRectangle();
            new Coin(rect);
        }

        // Bricks
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){

            rect =  ( (RectangleMapObject) object).getRectangle();
            new Brick(rect);
        }
    }
}
