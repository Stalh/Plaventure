package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Nature {
    protected World world;
    protected float width, height; //dimensions des bodies
    protected Body body;

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    protected int posX;
    protected int posY;
    protected int camHeight;
    protected int camWidth;
    protected Texture texture;
    protected Sprite sprite;

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }


    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }


    public Body getBody() {
        return body;
    }


    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }



   public Nature(World world){
       this.world = world;
       this.width = 1;
       this.height = 1;
       this.body  = null;  //par défaut, le body n'est pas défini.
       posX = 0;
       posY = 0;
       this.camHeight = 16;
       this.camWidth = 12;
       sprite = null;
       texture = null;
   }


   public String estDeType(){
       return "";
   }

   public void draw(SpriteBatch sb ){
       sprite.draw(sb);
   }
}
