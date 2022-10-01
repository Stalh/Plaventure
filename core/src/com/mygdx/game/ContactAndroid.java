package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;

public class ContactAndroid implements GestureDetector.GestureListener {
    Game game;


    public ContactAndroid(Game game){
    this.game = game;


    }


    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        if(Gdx.input.isTouched(0) && Gdx.input.isTouched(1)){
            if(game.getPlayer().getBody().getLinearVelocity().y==0){
                if(Gdx.input.getX() >= (Gdx.graphics.getWidth() / 2)){
                    game.getPlayer().getBody().applyForceToCenter(50f, 1000f, true);
                }else if (Gdx.input.getX() <= (Gdx.graphics.getWidth() / 2f)){
                    game.getPlayer().getBody().applyForceToCenter(-50f, 50f, true);

                }
            }
        }
        if(Gdx.input.isTouched(0)){
            if (Gdx.input.getX() >= (Gdx.graphics.getWidth() / 2)) {
                    game.getPlayer().getBody().applyForceToCenter(50f, 0f, true);
            } else if (Gdx.input.getX() <= (Gdx.graphics.getWidth() / 2f)) {
                    game.getPlayer().getBody().applyForceToCenter(-50f, 0f, true);
            }

        }



        return false;
    }
    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
