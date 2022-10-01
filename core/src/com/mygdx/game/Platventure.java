package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;

public class Platventure extends ApplicationAdapter {
     private Game game;
    @Override
    public void create () {
        game = new Game();
        game.create();
    }

    @Override
    public void dispose(){
        game.dispose();
    }

}
