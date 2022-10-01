package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Player;
import com.mygdx.game.Textures;

public class PlayerView {
    private final Player player;
    private final Sprite spritePlayer;

    public PlayerView(Player player) {
        this.player = player;
        spritePlayer = new Sprite(Textures.getPlayerTexture());
        spritePlayer.setCenter(player.getPosX(),player.getPosY());
        spritePlayer.setSize(0.5f,1);
    }

    public Sprite getSpritePlayer() {
        return spritePlayer;
    }

    public void move()
    {
        spritePlayer.setCenter(player.getBody().getPosition().x,player.getBody().getPosition().y);
    }


}
