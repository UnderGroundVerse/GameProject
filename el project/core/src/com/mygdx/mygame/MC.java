package com.mygdx.mygame;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

public class MC extends Sprite {
public World world;
public Body b2body;
    public MC(World world){
        this.world = world;
        defineMC();

    }

    public void defineMC(){
        //body definition
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(20,100);


        //shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(10,20);



        //fixture defination
        FixtureDef fdef = new FixtureDef();
        fdef.friction = 10f;




        b2body = world.createBody(bdef);
        fdef.shape =shape;
        b2body.createFixture(fdef);





    }

}
