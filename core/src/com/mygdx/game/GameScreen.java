package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen extends ScreenAdapter {
    private final Game game;
    private final Text text;

    public GameScreen(Game game){
        this.game = game;
        this.text = new Text();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0f,0f,0f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getDebugRenderer().render(game.getWorld(), game.getCamera().combined);

        game.batch.begin();
        if(game.getFileReader().getLevel().equals("levels/level_002.txt")){
            game.batch.draw(Textures.getBackTexture(), 0, 0  , game.getFileReader().getCamWidth() , game.getFileReader().getCamHeight());

        }else{
            game.batch.draw(Textures.getBackTexture(), 0, 0  , game.getCamera().viewportWidth , game.getCamera().viewportHeight);
        }
        for(Nature e : game.getFileReader().getElements()){
            e.draw(game.batch);
        }
        game.getPlayer().formationTexture();
        game.batch.end();

        game.batch.setProjectionMatrix(game.getCamera().combined);
        game.movePlayer(); // bouger le joueur
        text.afficherScore("Score = " + game.getScore());
        text.afficherTemps( "" + game.getCurrentGameTime());
        game.removeBodies();
        update(delta);
    }

    public void update(float delta){
        game.getWorld().step(1/60f,6,2);
        if(game.isGameover() || game.getCurrentGameTime()==0 ){
            game.resetGame();
        }else if(game.isWin()){
            game.nextLevel();
        }else{
            game.updateGameTime(delta);
            game.updateCamera();
        }
        //affichage de text
        if(game.isAfficherTextPerte()){
            text.afficherMessage("Dommage :/ ");
        }
        if(game.isAfficherTextWin()){
            text.afficherMessage("Bravo !");

        }
    }



    @Override
    public void dispose(){
        text.dispose();

    }






}
