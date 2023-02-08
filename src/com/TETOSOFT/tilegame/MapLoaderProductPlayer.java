package com.TETOSOFT.tilegame;


import com.TETOSOFT.graphics.Sprite;
import java.util.ArrayList;

public class MapLoaderProductPlayer {
	private Sprite playerSprite;

	public void setPlayerSprite(Sprite playerSprite) {
		this.playerSprite = playerSprite;
	}

	public Sprite player(ArrayList lines) {
		Sprite player = (Sprite) playerSprite.clone();
		player.setX(TileMapDrawer.tilesToPixels(3));
		player.setY(lines.size());
		return player;
	}

	public Sprite sprite(Sprite hostSprite, int tileX, int tileY) {
		Sprite sprite = (Sprite) hostSprite.clone();
		sprite.setX(TileMapDrawer.tilesToPixels(tileX) + (TileMapDrawer.tilesToPixels(1) - sprite.getWidth()) / 2);
		sprite.setY(TileMapDrawer.tilesToPixels(tileY + 1) - sprite.getHeight());
		return sprite;
	}
}