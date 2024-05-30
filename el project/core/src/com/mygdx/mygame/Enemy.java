package com.mygdx.mygame;

import com.badlogic.gdx.physics.box2d.BodyDef;

public class Enemy extends Body2D {

    public Enemy()
    {
        DefineBody();
        DefineShape();
        DefineFixture();
        super.CreateBody();
    }

    @Override
    public void DefineBody() {
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(100,100);
    }

    @Override
    public void DefineShape() {
        shape.setAsBox(10,20);
    }

    @Override
    public void DefineFixture() {
        fdef.friction = 10f;
        fdef.shape = shape;
    }
}
