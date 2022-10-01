package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Game;
import com.mygdx.game.Textures;

public class EcranIntro extends ScreenAdapter {
    private final Game game;
    public EcranIntro(Game game){
        this.game = game;
    }
    @Override
    public void show() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.setScreen(game.getGameScreen());
            }
        },3);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(Textures.getTextureIntro(), 0, 0, game.getCamera().viewportWidth , game.getCamera().viewportHeight);
        game.batch.end();
    }

}

