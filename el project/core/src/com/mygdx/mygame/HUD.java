package com.mygdx.mygame;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HUD {
    public Stage stage;
    private Viewport viewport;

    private int worldTimer;
    private float timeCount;
    private int score;

    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label marioLabel;
    Label worldLabel;

    public HUD(SpriteBatch sb){
        worldTimer = 932;
        timeCount = 0;
        score=0;

        viewport = new FitViewport(MyGame.V_WIDTH,MyGame.V_HEIGHT,new OrthographicCamera());
        stage=new Stage(viewport);
    }

}
