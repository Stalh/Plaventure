package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends Nature {

    public Player(World world, int i, int j, int camWidth, int camHeight){
        super(world);
        this.width = (float) 0.5;
        posX = i;
        posY = j;
        this.camHeight = camHeight;
        this.camWidth = camWidth;
        //créer le body du joueur

        BodyDef bd =  new BodyDef(); //on crée le body
        bd.position.set(posY +width ,camHeight - posX+ height/2);
        bd.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bd);
        body.setUserData("player");
        //losange
        PolygonShape losange = new PolygonShape();

        Vector2[] vertices = new Vector2[5];  // An array of 4 vectors
        vertices[0] = new Vector2(0, height/4);
        vertices[1] = new Vector2(-width/2, 0);
        vertices[2] = new Vector2(0, height/2);
        vertices[3] = new Vector2(width/2 , 0);
        vertices[4] = new Vector2(0, -height/4);

        losange.set(vertices);

        //Cercle
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(height/8);

        FixtureDef fixtureLosange = new FixtureDef();
        fixtureLosange.shape = losange;
        body.createFixture(fixtureLosange).setUserData("player");

        fixtureLosange.density = 0.5F;
        fixtureLosange.friction= 0.5F;
        fixtureLosange.restitution = 0.1F;

        Vector2 offset = new Vector2(0, (-height/3));
        circleShape.setPosition(offset);

        FixtureDef fixtureCircle = new FixtureDef();
        fixtureCircle.shape = circleShape;
        body.createFixture(fixtureCircle).setUserData("player");

        fixtureCircle.density =0.5F;
        fixtureCircle.friction= 0.5F;
        fixtureCircle.restitution = 0.1F;

        losange.dispose();
        circleShape.dispose();


        formationTexture();
    }

     public void formationTexture(){
         this.sprite = new Sprite(Textures.getPlayerTexture());
         sprite.setBounds(this.getBody().getPosition().x - 0.5f, this.getBody().getPosition().y - 0.5f, 1, 1);
     }

    @Override
    public String toString(){
        return "joueur";
    }

    @Override
    public String estDeType() {
        return "Player";
    }


    public boolean retombe(){
        float x = body.getLinearVelocity().y;
        return x != 0;
    }
}
