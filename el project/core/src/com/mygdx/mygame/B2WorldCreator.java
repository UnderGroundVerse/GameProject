package com.mygdx.mygame;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

public class B2WorldCreator extends Body2D
{
    Rectangle rect;
    public B2WorldCreator(Map map)
    {
        super();
        rect = new Rectangle();

        for(MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            DefineBody();
            DefineShape();
            DefineFixture();
            rect =  ( (RectangleMapObject) object).getRectangle();
            CreateBody();
        }
    }

    @Override
    public void DefineBody()
    {
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(rect.getX()+rect.getWidth()/2 , rect.getY()+rect.getHeight()/2);
    }

    @Override
    public void DefineShape() {
        shape.setAsBox(rect.getWidth()/2, rect.getHeight()/2);
    }

    @Override
    public void DefineFixture() {
        fdef.shape = shape;
    }

    @Override
    public void CreateBody() {
        super.CreateBody();
    }
}
