package com.mygdx.mygame;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public abstract class Body2D
{
    Body b2body;
    BodyDef bdef;
    PolygonShape shape;
    FixtureDef fdef;
    Rectangle rectangle;

    public Body2D(Rectangle rectangle)
    {
        bdef = new BodyDef();
        shape = new PolygonShape();
        fdef = new FixtureDef();
        this.rectangle = rectangle;

        DefineBody();
        DefineShape();
        DefineFixture();
        CreateBody();
    }

    public abstract void DefineBody();

    public void DefineShape()
    {
        shape.setAsBox(rectangle.getWidth() / 2,rectangle.getHeight() / 2);
    }

    public void DefineFixture()
    {
        fdef.shape = shape;
    }

    public void CreateBody()
    {
        b2body = PlayScreen.world.createBody(bdef);
        b2body.createFixture(fdef);
    }
}
