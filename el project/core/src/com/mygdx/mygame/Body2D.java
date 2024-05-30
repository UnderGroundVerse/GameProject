package com.mygdx.mygame;

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

    public Body2D()
    {
        bdef = new BodyDef();
        shape = new PolygonShape();
        fdef = new FixtureDef();
    }

    public abstract void DefineBody();
    public abstract void DefineShape();
    public abstract void DefineFixture();

    public void CreateBody()
    {
        b2body = PlayScreen.world.createBody(bdef);
        b2body.createFixture(fdef);
    }
}
