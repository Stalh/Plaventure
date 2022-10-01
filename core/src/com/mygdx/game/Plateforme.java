package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Plateforme extends Nature {
    private final String type;
    public Plateforme(World world, String type, int i , int j, int camWidth, int camHeight){
        super(world);
        posX = i;
        posY = j;
        this.camHeight = camHeight;
        this.type = type;
        this.height = (float) 0.75; //



        BodyDef bd = new BodyDef(); //on crée le body
        bd.position.set(posY +width/2 ,camHeight - posX+ height/2);
        bd.type = BodyDef.BodyType.StaticBody; //il est statique
        PolygonShape rectangle = new PolygonShape();

        //Selon le type de la platforme on a :
        //partie centrale
        if(type.equals("K")){
            rectangle.setAsBox(width/2, height/2);
        }else if (type.equals("J")){ // coté gauche
            Vector2[] vertices = new Vector2[5];  // un tableau de 5 vecteurs
            vertices[0] =new Vector2(-width/2, 0);
            vertices[1] = new Vector2(0, -height/2);
            vertices[2] = new Vector2(width/2, -height/2);
            vertices[3] = new Vector2(width/2, height/2);
            vertices[4] = new Vector2(-width/2, height/2);
            rectangle.set(vertices);
        }else if(type.equals("L")){
            Vector2[] vertices = new Vector2[5];  // An array of 4 vectors
            vertices[0] = new Vector2(-width/2, height/2);
            vertices[1] = new Vector2(width/2, 0);
            vertices[2] = new Vector2(width/2, height/2);
            vertices[3] = new Vector2(0, -height/2);
            vertices[4] = new Vector2(-width/2, -height/2);
            rectangle.set(vertices);
        }
        FixtureDef fd = new FixtureDef();
        fd.shape = rectangle;
        fd.density = 1;
        fd.friction= 0.25F;

        this.body = world.createBody(bd);
        body.createFixture(fd).setUserData("platform");
        rectangle.dispose();



        this.texture = new Texture(Gdx.files.internal("images/Platform_"+type+".png"));
        this.sprite  = new Sprite(this.texture);
        sprite.setBounds(this.getBody().getPosition().x-width/2f, this.getBody().getPosition().y-height/2f, width, height);

    }

    @Override
    public String toString(){
        return "platform";
    }

    @Override
    public String estDeType() {
        return "Platform";
    }
}
