package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import java.util.ArrayList;

public class Collisions implements ContactListener {

    private final Player player;
    private final Game game;
    private  boolean cpt;
    public ArrayList<Body> getBodiesToRemove() {
        return bodiesToRemove;
    }

    private final ArrayList<Body> bodiesToRemove;

    public Collisions(Game game, Player player){
        this.player = player;
        this.game = game;
        bodiesToRemove = new ArrayList<>(10);
        cpt = false;
    }


    @Override
    public void beginContact(Contact contact) {

        //on récupère les fixtures
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

       if(fa.getUserData() == "gem1" &&  fb.getUserData() == "player"){
            bodiesToRemove.add(fa.getBody());
            Son.collecterGemmes();
       }

       else if(fa.getUserData() == "player" && fb.getUserData() == "gem1"){
            bodiesToRemove.add(fb.getBody());
            Son.collecterGemmes();
       }

       else if(fa.getUserData() == "gem2" &&  fb.getUserData() == "player"){
            bodiesToRemove.add(fa.getBody());
            Son.collecterGemmes();
       }

       else if(fa.getUserData() == "player" && fb.getUserData() == "gem2"){
           bodiesToRemove.add(fb.getBody());
           Son.collecterGemmes();
       }

       else if(fa.getUserData() == "water" && fb.getUserData()=="player"){
           game.setGameover(true);
           Son.plouf();
           Son.perdu();
       }

       else if(fa.getUserData() == "player" && fb.getUserData()=="water"){
           game.setGameover(true);
           Son.plouf();
           Son.perdu();
       }

       else if(player.retombe()){
           if(game.getPlayer().getBody().getPosition().x<0 || game.getPlayer().getBody().getPosition().x>game.getCamera().viewportWidth ){
               game.setGameover(true);
               Son.perdu();
           }
            Son.collisionSimple();
       }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if((fa.getUserData() == "exit" && fb.getUserData() == "player")){
            //le joueur a gagné changement de niveau
            if(!bodiesToRemove.contains(fb.getBody())){
                bodiesToRemove.add(fb.getBody());
                game.setWin(true);
            }
        }


    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
