package com.TETOSOFT.tilegame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.LinkedList;
import java.util.Iterator;

import com.TETOSOFT.graphics.Sprite;
import com.TETOSOFT.tilegame.sprites.Creature;

/**
    The TileMap class contains the data for a tile-based
    map, including Sprites. Each tile is a reference to an
    Image. Of course, Images are used multiple times in the tile
    map.
*/
public class TileMap {

    private TileMapProduct tileMapProduct = new TileMapProduct();
	private Image[][] tiles;
    private Sprite player;

    /**
        Creates a new TileMap with the specified width and
        height (in number of tiles) of the map.
    */
    public TileMap(int width, int height) {
        tiles = new Image[width][height];
        tileMapProduct.setSprites(new LinkedList());
    }


    /**
        Gets the width of this TileMap (number of tiles across).
    */
    public int getWidth() {
        return tiles.length;
    }


    /**
        Gets the height of this TileMap (number of tiles down).
    */
    public int getHeight() {
        return tiles[0].length;
    }


    /**
        Gets the tile at the specified location. Returns null if
        no tile is at the location or if the location is out of
        bounds.
    */
    public Image getTile(int x, int y) {
        if (x < 0 || x >= getWidth() ||
            y < 0 || y >= getHeight())
        {
            return null;
        }
        else {
            return tiles[x][y];
        }
    }


    /**
        Sets the tile at the specified location.
    */
    public void setTile(int x, int y, Image tile) {
        tiles[x][y] = tile;
    }


    /**
        Gets the player Sprite.
    */
    public Sprite getPlayer() {
        return player;
    }


    /**
        Sets the player Sprite.
    */
    public void setPlayer(Sprite player) {
        this.player = player;
    }


    /**
        Adds a Sprite object to this map.
    */
    public void addSprite(Sprite sprite) {
        tileMapProduct.getSprites2().add(sprite);
    }


    /**
        Removes a Sprite object from this map.
    */
    public void removeSprite(Sprite sprite) {
        tileMapProduct.removeSprite(sprite);
    }


    /**
        Gets an Iterator of all the Sprites in this map,
        excluding the player Sprite.
    */
    public Iterator getSprites() {
        return tileMapProduct.getSprites();
    }


	public int offsetX(int screenWidth) {
		Sprite player = getPlayer();
		int mapWidth = TileMapDrawer.tilesToPixels(getWidth());
		int offsetX = screenWidth / 2 - Math.round(player.getX()) - TileMapDrawer.TILE_SIZE;
		offsetX = Math.min(offsetX, 0);
		offsetX = Math.max(offsetX, screenWidth - mapWidth);
		return offsetX;
	}


	/**
	 * Draws the specified TileMap.
	 * @param background
	 */
	public void draw(Graphics2D g, int screenWidth, int screenHeight, Image background) {
		int offsetX = offsetX(screenWidth);
		Sprite player = getPlayer();
		int mapWidth = TileMapDrawer.tilesToPixels(getWidth());
		int offsetY = screenHeight - TileMapDrawer.tilesToPixels(getHeight());
		if (background == null || screenHeight > background.getHeight(null)) {
			g.setColor(Color.black);
			g.fillRect(0, 0, screenWidth, screenHeight);
		}
		if (background != null) {
			int x = offsetX * (screenWidth - background.getWidth(null)) / (screenWidth - mapWidth);
			int y = screenHeight - background.getHeight(null);
			g.drawImage(background, x, y, null);
		}
		int firstTileX = TileMapDrawer.pixelsToTiles(-offsetX);
		int lastTileX = firstTileX + TileMapDrawer.pixelsToTiles(screenWidth) + 1;
		for (int y = 0; y < getHeight(); y++) {
			for (int x = firstTileX; x <= lastTileX; x++) {
				Image image = getTile(x, y);
				if (image != null) {
					g.drawImage(image, TileMapDrawer.tilesToPixels(x) + offsetX,
							TileMapDrawer.tilesToPixels(y) + offsetY, null);
				}
			}
		}
		g.drawImage(player.getImage(), Math.round(player.getX()) + offsetX, Math.round(player.getY()) + offsetY, null);
		Iterator i = getSprites();
		while (i.hasNext()) {
			Sprite sprite = (Sprite) i.next();
			int x = Math.round(sprite.getX()) + offsetX;
			int y = Math.round(sprite.getY()) + offsetY;
			g.drawImage(sprite.getImage(), x, y, null);
			if (sprite instanceof Creature && x >= 0 && x < screenWidth) {
				((Creature) sprite).wakeUp();
			}
		}
	}

}
