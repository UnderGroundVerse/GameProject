package com.mygdx.mygame;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;

public class Pipe extends Body2D {

    public Pipe(Rectangle rectangle)
    {
        super(rectangle);
    }

    @Override
    public void DefineBody()
    {
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(rectangle.x + (rectangle.getWidth() / 2), rectangle.y + (rectangle.getHeight() / 2));
    }
}
