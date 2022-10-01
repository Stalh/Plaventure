package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.Game;

public class Text {
    public SpriteBatch textBatch;
    public BitmapFont font;


    public Text(){
        font = new BitmapFont();

        //CAM
        OrthographicCamera textCamera = new OrthographicCamera();
        textCamera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        textBatch = new SpriteBatch();


        //GENERATE
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Comic_Sans_MS_Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();

        //PARAMS
        params.size=Gdx.graphics.getWidth()*50/1024;
        params.color = new Color(255,255,0,0.75f);
        params.borderColor = Color.BLACK;
        params.borderWidth = (float)(Gdx.graphics.getWidth()*3)/1024;
        params.characters = FreeTypeFontGenerator.DEFAULT_CHARS ;

        //CAM
        textBatch.setProjectionMatrix(textCamera.combined);
        font = fontGenerator.generateFont(params);

    }


    public void afficherMessage(String message){
        textBatch.begin();
        font.draw(textBatch,message, (float)Gdx.graphics.getWidth()/2, (float)Gdx.graphics.getHeight()/2,message.length(),1,false);
        textBatch.end();
    }

    public void afficherScore(String message){
        textBatch.begin();
        float posX = (float)(Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/6);
        float posY = (float)(Gdx.graphics.getHeight() - 10);
        font.draw(textBatch, message,posX, posY, message.length(), 1, false);
        textBatch.end();
    }


    public void afficherTemps(String message){
        textBatch.begin();
        float posX = (float)(Gdx.graphics.getWidth()/2);
        float posY = (float)(Gdx.graphics.getHeight() - 10);
        font.draw(textBatch, message,posX, posY, message.length(), 1, false);
        textBatch.end();
    }

    public void dispose(){
        //font.dispose();
        //textBatch.dispose();

    }

}
