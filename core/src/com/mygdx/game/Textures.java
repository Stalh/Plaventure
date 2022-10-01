package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Textures {

    private static Textures instance = new Textures();

    private Textures() {

    }

    public static Textures getInstance(){
        if (instance == null)
        {
            instance = new Textures();
        }

        return instance;
    }

    private final static Texture textureIntro = new Texture(Gdx.files.internal("images/Intro.png"));
    private final static Texture playerTexture = new Texture(Gdx.files.internal("images/Idle__000.png"));
    private final static Texture brickATexture = new Texture(Gdx.files.internal("images/Brick_A.png"));
    private final static Texture brickBTexture = new Texture(Gdx.files.internal("images/Brick_B.png"));
    private final static Texture brickCTexture = new Texture(Gdx.files.internal("images/Brick_C.png"));
    private final static Texture brickDTexture = new Texture(Gdx.files.internal("images/Brick_D.png"));
    private final static Texture brickETexture = new Texture(Gdx.files.internal("images/Brick_E.png"));
    private final static Texture brickFTexture = new Texture(Gdx.files.internal("images/Brick_F.png"));
    private final static Texture brickGTexture = new Texture(Gdx.files.internal("images/Brick_G.png"));
    private final static Texture brickHTexture = new Texture(Gdx.files.internal("images/Brick_H.png"));
    private final static Texture brickITexture = new Texture(Gdx.files.internal("images/Brick_I.png"));
    private final static Texture exitTexture = new Texture (Gdx.files.internal("images/Exit_Z.png"));
    private final static Texture backTexture = new Texture(Gdx.files.internal("images/Back.png"));
    private final static Texture gem1Texture = new Texture(Gdx.files.internal("images/Gem_1.png"));
    private final static Texture gem2Texture = new Texture(Gdx.files.internal("images/Gem_2.png"));

    public static Texture getExitTexture() {
        return exitTexture;
    }

    public static Texture getBackTexture() {
        return backTexture;
    }

    public static Texture getGem1Texture() {
        return gem1Texture;
    }

    public static Texture getGem2Texture() {
        return gem2Texture;
    }

    public static Texture getTextureIntro() {
        return textureIntro;
    }

    public static Texture getPlayerTexture() {
        return playerTexture;
    }

    public static Texture getBrickTexture(String type){
        switch (type){
            case "A":
                return brickATexture;
            case "B":
                return brickBTexture;
            case "C":
                return brickCTexture;
            case "D":
                return brickDTexture;
            case "E":
                return brickETexture;
            case "F":
                return brickFTexture;
            case "G":
                return brickGTexture;
            case "H":
                return brickHTexture;
            case "I":
                return  brickITexture;
            case "Z":
                return exitTexture;
        }
        return brickATexture;
    }
}

