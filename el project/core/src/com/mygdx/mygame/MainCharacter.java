package com.mygdx.mygame;

import com.badlogic.gdx.physics.box2d.*;

public class MainCharacter extends Body2D
{
    private static MainCharacter instance;

    private MainCharacter()
    {
        DefineBody();
        DefineShape();
        DefineFixture();
        super.CreateBody();
    }

    @Override
    public void DefineBody() {
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(20,100);
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

    public static MainCharacter GetMainCharacter()
    {
        if(instance == null)
        {
            instance = new MainCharacter();
        }

        return instance;
    }
}
