package com.mygdx.mygame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;

public class ListenerClass implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        Gdx.app.log("contact begin", "between " + a.toString() + " and " + b.toString());
    }

    @Override
    public void endContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        Gdx.app.log("contact end", "between " + a.toString() + " and " + b.toString());
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
