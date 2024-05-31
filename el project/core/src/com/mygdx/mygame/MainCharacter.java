package com.mygdx.mygame;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

public class MainCharacter extends Body2D
{
    private static MainCharacter instance;
    Sprite sprite;
    static Rectangle rectangle;

    private MainCharacter(Rectangle rectangle)
    {
        super(rectangle);

    }

    @Override
    public void DefineBody()
    {
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(rectangle.x + (rectangle.getWidth() / 2), rectangle.y + (rectangle.getHeight() / 2));
    }

    public static MainCharacter GetMainCharacter()
    {
        if(instance == null)
        {
            rectangle = new Rectangle(64, 16, 16, 32);
            instance = new MainCharacter(rectangle);
        }

        return instance;
    }
}
