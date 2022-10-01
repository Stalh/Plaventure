package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Son {

    private static Son instance = new Son();

    private final static Sound intro = Gdx.audio.newSound(Gdx.files.internal("sounds/win.ogg"));
    private final static Sound noyade = Gdx.audio.newSound(Gdx.files.internal("sounds/plouf.ogg"));
    private final static Sound perte = Gdx.audio.newSound(Gdx.files.internal("sounds/loose.ogg"));
    private final static Sound lesGemmes = Gdx.audio.newSound(Gdx.files.internal("sounds/gem.ogg"));
    private final static Sound bricks = Gdx.audio.newSound(Gdx.files.internal("sounds/collision.ogg"));
    private final static Sound alerte = Gdx.audio.newSound(Gdx.files.internal("sounds/alert.ogg"));

    private final static float volume = 10;

    public static Son getInstance(){
        if (instance == null) {
            instance = new Son();
        }
        return instance;
    }

    public static void lancerintro() {
        intro.play(volume);
    }

    public static void plouf() {
        noyade.play(volume );
    }

    public static void perdu() {
        perte.play(volume);
    }

    public static void collecterGemmes(){
        lesGemmes.play(volume);
    }

    public static void collisionSimple(){
        bricks.play(volume);
    }

    public static void midGame() {
        alerte.play(volume);
    }


    public void dispose() {
        intro.dispose();
        lesGemmes.dispose();
        noyade.dispose();
        bricks.dispose();
        perte.dispose();
        alerte.dispose();
    }

}

