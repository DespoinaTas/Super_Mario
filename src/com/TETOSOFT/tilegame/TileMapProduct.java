package com.TETOSOFT.tilegame;


import java.util.LinkedList;
import com.TETOSOFT.graphics.Sprite;
import java.util.Iterator;

public class TileMapProduct {
	private LinkedList sprites;

	public LinkedList getSprites2() {
		return sprites;
	}

	public void setSprites(LinkedList sprites) {
		this.sprites = sprites;
	}

	/**
	* Removes a Sprite object from this map.
	*/
	public void removeSprite(Sprite sprite) {
		sprites.remove(sprite);
	}

	/**
	* Gets an Iterator of all the Sprites in this map, excluding the player Sprite.
	*/
	public Iterator getSprites() {
		return sprites.iterator();
	}
}