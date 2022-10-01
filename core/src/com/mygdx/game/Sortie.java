package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Sortie extends Nature {
    public Sortie(World w , int i , int j, int camWidth, int camHeight){
        super(w);

        BodyDef bd = new BodyDef(); //on crée le body
        bd.position.set(j +width/2 ,camHeight - i+ height/2);

        bd.type = BodyDef.BodyType.StaticBody;
        PolygonShape carre = new PolygonShape();
        carre.setAsBox(width/2, height/2);

        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = carre;
        Body brick = world.createBody(bd);

        brick.createFixture(fixtureDef1);

        carre.dispose();
    }
}
