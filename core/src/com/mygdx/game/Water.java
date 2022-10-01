package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Water extends Nature {
    public Water(World world, int i , int j, int camWidth, int camHeight){
        super(world);
        posX = i;
        posY = j;
        this.camHeight = camHeight;
        createWater();
        this.texture =  new Texture(Gdx.files.internal("images/Water.png"));
        this.sprite  = new Sprite(this.texture);
        sprite.setBounds(this.getBody().getPosition().x-width/2f, this.getBody().getPosition().y-height/2f, width, height);
    }


    public void createWater(){
        BodyDef bd =new BodyDef();
        this.height = (float)0.5;
        bd.position.set(posY+width/2, camHeight - posX +height/2);
        bd.type = BodyDef.BodyType.StaticBody;

        PolygonShape carre = new PolygonShape();
        carre.setAsBox(width/2, height);

        FixtureDef fixtureDef1 = new FixtureDef();
        fixtureDef1.shape = carre;
        body= world.createBody(bd);
        body.setUserData("water");
        Fixture fix = body.createFixture(fixtureDef1);
        fix.setSensor(true);
        fix.setUserData("water");
        carre.dispose();
    }

    @Override
    public String estDeType() {
        return "Water";
    }
}
