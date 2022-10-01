package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import jdk.internal.org.jline.utils.Display;

public class Game extends com.badlogic.gdx.Game {
    private World world;

    public FileReader getFileReader() {
        return fileReader;
    }

    private FileReader fileReader;
    private boolean gameover;
    private boolean win;
    private int score;
    private Player player;
    public  SpriteBatch batch;
    private Collisions col;

    public boolean isAfficherTextPerte() {
        return afficherTextPerte;
    }

    public void setAfficherTextPerte(boolean afficherTextPerte) {
        this.afficherTextPerte = afficherTextPerte;
    }

    private boolean afficherTextPerte;

    public boolean isAfficherTextWin() {
        return afficherTextWin;
    }

    public void setAfficherTextWin(boolean afficherTextWin) {
        this.afficherTextWin = afficherTextWin;
    }

    private boolean afficherTextWin;
    private OrthographicCamera camera;
    private Viewport vp;
    private Box2DDebugRenderer debugRenderer;
    private GameScreen gameScreen;
    private int gameTime;
    private float timeCount;
    private EcranIntro titleScreen;

    public Game() {
    }

    @Override
    public void create() {
        Son.lancerintro();
        this.world = new World(new Vector2(0, -10f), false);
        win = false;
        gameover = false;
        camera  = new OrthographicCamera(16, (16f * Gdx.graphics.getHeight())/Gdx.graphics.getWidth());
        vp  = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);
        vp.apply();
        timeCount =0;
        score = 0;
        afficherTextPerte = false;
        fileReader = new FileReader(world );
        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();
        readLevel("levels/level_001.txt");
        initCollisions();
        titleScreen = new EcranIntro(this);
        setScreen(titleScreen);
    }


    public void collectGems(int score){
        this.score+= score;

    }


    public void readLevel(String level){
        win = false;
        gameover = false;
        fileReader.readFile(level);
        player = fileReader.getPlayer();
        gameTime = fileReader.getTime();
        camera.setToOrtho(false, 16, (16f * Gdx.graphics.getHeight())/Gdx.graphics.getWidth());

        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);

        batch.setProjectionMatrix(camera.combined);
        gameScreen = new GameScreen(this);
        setScreen(new GameScreen(this));
        camera.update();
    }


    public void updateCamera(){
        float x;
        x = player.getBody().getPosition().x;
        if ((x< camera.viewportWidth/2f) || ( x > (float) fileReader.getCamWidth() - camera.viewportWidth/2f)){
            x = camera.position.x;
        }
        float y;
        y = player.getBody().getPosition().y;
        if ((y < camera.viewportHeight/2f) || (y > (float) fileReader.getCamHeight() - camera.viewportHeight/2f)){
            y = camera.position.y;
        }
        camera.position.set(x, y, 0);
        camera.update();
    }

    @Override
    public void resize(int width, int height){
        vp.update(width, height);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
        batch.setProjectionMatrix(camera.combined);
        camera.update();
    }


    public void initCollisions(){
        col = new Collisions(this, player);
        world.setContactListener(col);
    }


    public void upgradeLevel(){
        if(fileReader.getLevel().equals("levels/level_001.txt")){
            destroyAllBodies();
            readLevel("levels/level_002.txt");
        }else if(fileReader.getLevel().equals("levels/level_002.txt")){
            destroyAllBodies();
            readLevel("levels/level_003.txt");
        }else if(fileReader.getLevel().equals("levels/level_003.txt")){
            destroyAllBodies();
            readLevel("levels/level_001.txt");
        }
        afficherTextWin=false;

    }


    public void restartLevel(){
        if(fileReader.getLevel().equals("levels/level_001.txt")){
            destroyAllBodies();
            readLevel("levels/level_001.txt");
        }else if(fileReader.getLevel().equals("levels/level_002.txt")){
            destroyAllBodies();
            readLevel("levels/level_002.txt");
        }else if(fileReader.getLevel().equals("levels/level_003.txt")){
            destroyAllBodies();
            readLevel("levels/level_003.txt");
        }
        afficherTextPerte = false;


    }


    public void destroyAllBodies(){
        Array<Body> bodies = new Array<>();
        world.getBodies(bodies);
        for(int i = 0; i < bodies.size; i++){
            if(!world.isLocked())
                world.destroyBody(bodies.get(i));
        }
        win = false;
    }



    public void removeBodies() {
        //on récupère la liste des bodies à supprimer et on les destroy
        ArrayList<Body> bodiesToRemove = col.getBodiesToRemove();
        if(!isWin()){
            for (Body b : bodiesToRemove) {
                for(Nature e : getFileReader().getElements()){
                    if(e.getBody().equals(b)){
                        e.getSprite().setColor(0, 0, 0, 0.0f);
                    }
                }

                if(b.getUserData().equals("gem1")){ //ici on  fait la difference entre les deux gemmes leurs permettant d'etre diffenrece aà l'affichage
                    collectGems(1);
                }else if (b.getUserData().equals("gem2")){
                    collectGems(2);
                }
                world.destroyBody(b);

            }
        }
        bodiesToRemove.clear();
    }


    @Override
    public void dispose() {
        debugRenderer.dispose();
        gameScreen.dispose();
        titleScreen.dispose();
        Son.getInstance().dispose();

    }


    public void updateGameTime(float delta) {
        timeCount+=delta;
        if(timeCount >=1){
            gameTime--;
            timeCount= 0;
        }
        //lancer un son d'alerte à la moitié du temps de jeu
        if(gameTime== fileReader.getTime()/2){
            Son.midGame();
        }
    }

    public void movePlayer(){
        //si on est sur desktop
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT) ){        //se déplacer à droite
                System.out.println("");
                //player.getSprite().flip(false, false);
                player.getBody().applyForceToCenter(6f, 0f, true);
            } else if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT) ){ // se déplacer à gauche
                player.getBody().applyForceToCenter(-6f, 0f, true);
                if(!player.getSprite().isFlipX()){
                    player.getSprite().flip(true, false);

                }
            } else if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) &&  player.getBody().getLinearVelocity().y==0) {
                player.getBody().applyForceToCenter(0f, 400f, true);
            } else if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
                Gdx.app.exit();
            }
        }


        //**** PARTI ANDROID ****

        if (Gdx.app.getType() == Application.ApplicationType.Android){
            ContactAndroid eg = new ContactAndroid(this);
            GestureDetector gl = new GestureDetector(eg);
            Gdx.input.setInputProcessor(gl);
        }

    }

    public void resetGame(){
        setAfficherTextPerte(true);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                restartLevel();

            }
        },3);

        System.out.println("update");
        setGameover(false);

    }

    public void nextLevel(){
        setAfficherTextWin(true);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                upgradeLevel();
            }
        },3);
        setWin(false);

    }


    public int getScore() {
        return score;
    }

    public Box2DDebugRenderer getDebugRenderer() {
        return debugRenderer;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isGameover() {
        return gameover;
    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }

    public int getCurrentGameTime() {
        return gameTime;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public World getWorld() {
        return world;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

}
