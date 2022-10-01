package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Brick extends Nature {
    public String getType() {
        return type;
    }

    private final String type;

    public Brick(World world, String type, int i, int j , int camWidth , int camHeight) {
        super(world);
        posX = i;
        posY = j;
        this.camHeight = camHeight;
        this.type = type;
        BodyDef bd = new BodyDef(); //on crée le body
        bd.position.set(posY + width / 2, camHeight - posX + height / 2);


        bd.type = BodyDef.BodyType.StaticBody; //il est statique
        PolygonShape carre = new PolygonShape();    //les briques sont représentées par des carrés
        carre.setAsBox(width/2, height/2);

        FixtureDef fixtureDef1 = new FixtureDef(); //
        fixtureDef1.shape = carre;
        this.body = world.createBody(bd);

        Fixture fix = this.body.createFixture(fixtureDef1);

        if(type.equals("Z")){
            fix.setSensor(true);
            fix.setUserData("exit");
        }else{
            fix.setUserData("brick");
        }

        carre.dispose();
        if (type.equals("Z")) {
            this.texture = Textures.getExitTexture();
        } else {
            this.texture = new Texture(Gdx.files.internal("images/Brick_" + type + ".png"));

        }
        this.sprite = new Sprite(this.texture);
        sprite.setBounds(this.getBody().getPosition().x - 0.5f, this.getBody().getPosition().y - 0.5f, 1, 1);

    }

    public String toString(){
        return "Brick";
    }

    @Override
    public String estDeType() {
        return "Brick";
    }
}
