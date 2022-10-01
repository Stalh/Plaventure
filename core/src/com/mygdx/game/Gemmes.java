package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Gemmes extends Nature {
    public int getType() {
        return type;
    }

    private final int type;
    public Gemmes(World world , int type, int i, int j , int camWidth, int camHeight){
        super(world);
        posX = i;
        posY = j;
        this.camHeight = camHeight;
        this.type = type;

        BodyDef bd = new BodyDef();
        bd.position.set(posY+width/2 , (float) (camHeight+0.5-posX)); //au pif

        bd.type = BodyDef.BodyType.StaticBody; //il est statique

        CircleShape circle = new CircleShape();    //les diamants sont représentés par des cercles
        circle.setRadius(width/4);

        FixtureDef fixtureDef1 = new FixtureDef(); //
        fixtureDef1.shape = circle;
        this.body = world.createBody(bd);

        Fixture fixture =  body.createFixture(fixtureDef1);
        fixture.setSensor(true); //traversable

        if( type == 1){
            //afficher un diamant jaune
            fixture.setUserData("gem1");
            body.setUserData("gem1");

        }else{
            //afficher un diamant orange
            fixture.setUserData("gem2");
            body.setUserData("gem2");
        }
        circle.dispose();

        if(this.getBody().getUserData().equals("gem1")){
            this.texture = Textures.getGem1Texture();

        }else if (this.getBody().getUserData().equals("gem2")){
            this.texture = Textures.getGem2Texture();

        }
        this.sprite = new Sprite(this.texture);
        sprite.setBounds(this.getBody().getPosition().x-0.2f , this.getBody().getPosition().y-0.2f, width/2, height/2);
    }

    @Override
    public String estDeType() {
        return "Gem";
    }
}
