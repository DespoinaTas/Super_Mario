package com.TETOSOFT.graphics;

import com.TETOSOFT.tilegame.TileMap;
import com.TETOSOFT.tilegame.TileMapDrawer;
import java.awt.Image;
import java.awt.Point;

public class Sprite {

    private SpriteProduct spriteProduct = new SpriteProduct();

	protected Animation anim;
    
    public Sprite(Animation anim) 
    {
        this.anim = anim;
    }

    
    
    public void update(long elapsedTime) 
    {
        spriteProduct.update(elapsedTime, this.anim);
    }

    
    public float getX() 
    {
        return spriteProduct.getX();
    }

    
    public float getY() {
        return spriteProduct.getY();
    }

    
    public void setX(float x) 
    {
        spriteProduct.setX(x);
    }

   
    public void setY(float y)
    {
        spriteProduct.setY(y);
    }

   
    public int getWidth() 
    {
        return anim.getImage().getWidth(null);
    }

    
    public int getHeight() 
    {
        return anim.getImage().getHeight(null);
    }

    
    public float getVelocityX() 
    {
        return spriteProduct.getDx();
    }

   
    public float getVelocityY()
    {
        return spriteProduct.getDy();
    }

    
    public void setVelocityX(float dx) 
    {
        spriteProduct.setDx(dx);
    }

    
    public void setVelocityY(float dy) 
    {
        spriteProduct.setDy(dy);
    }

    
    public Image getImage() 
    {
        return anim.getImage();
    }

    
    public Object clone() 
    {
        return new Sprite(anim);
    }



	/**
	 * Gets the tile that a Sprites collides with. Only the Sprite's X or Y should be changed, not both. Returns null if no collision is detected.
	 * @param map
	 * @param pointCache
	 */
	public Point getTileCollision(float newX, float newY, TileMap map, Point pointCache) {
		float fromX = Math.min(getX(), newX);
		float fromY = Math.min(getY(), newY);
		float toX = Math.max(getX(), newX);
		float toY = Math.max(getY(), newY);
		int fromTileX = TileMapDrawer.pixelsToTiles(fromX);
		int fromTileY = TileMapDrawer.pixelsToTiles(fromY);
		int toTileX = TileMapDrawer.pixelsToTiles(toX + getWidth() - 1);
		int toTileY = TileMapDrawer.pixelsToTiles(toY + getHeight() - 1);
		for (int x = fromTileX; x <= toTileX; x++) {
			for (int y = fromTileY; y <= toTileY; y++) {
				if (x < 0 || x >= map.getWidth() || map.getTile(x, y) != null) {
					pointCache.setLocation(x, y);
					return pointCache;
				}
			}
		}
		return null;
	}
}
