package com.TETOSOFT.tilegame;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import com.TETOSOFT.graphics.*;
import com.TETOSOFT.tilegame.sprites.*;


/**
    The ResourceManager class loads and manages tile Images and
    "host" Sprites used in the game. Game Sprites are cloned from
    "host" Sprites.
*/
public class MapLoader 
{
    private MapLoaderProduct4 mapLoaderProduct4 = new MapLoaderProduct4();
	private MapLoaderProduct2 mapLoaderProduct2 = new MapLoaderProduct2();
	public int currentMap;
    /**
        Creates a new ResourceManager with the specified
        GraphicsConfiguration.
    */
    public MapLoader(GraphicsConfiguration gc) 
    {
        mapLoaderProduct2.setGc(gc);
        mapLoaderProduct4.getMapLoaderProduct().loadTileImages();
        loadCreatureSprites();
        mapLoaderProduct4.loadPowerUpSprites();
    }


    /**
        Gets an image from the images/ directory.
    */
    public Image loadImage(String name) 
    {
        return mapLoaderProduct4.getMapLoaderProduct().loadImage(name);
    }


    public Image getMirrorImage(Image image) 
    {
        return mapLoaderProduct2.getScaledImage(image, -1, 1);
    }


    public Image getFlippedImage(Image image) 
    {
        return mapLoaderProduct2.getScaledImage(image, 1, -1);
    }


    public TileMap loadNextMap() 
    {
        TileMap map = null;
        while (map == null) 
        {
            currentMap++;
            try {
                map = mapLoaderProduct4.loadMap(
                    "maps/map" + currentMap + ".txt", this);
            }
            catch (IOException ex) 
            {
                if (currentMap == 2) 
                {
                    // no maps to load!
                    return null;
                }
                  currentMap = 0;
                map = null;
            }
        }

        return map;
    }


    public TileMap reloadMap() 
    {
        try {
            return mapLoaderProduct4.loadMap(
                "maps/map" + currentMap + ".txt", this);
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public int width(String filename) throws FileNotFoundException, IOException {
		int width = 0;
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		while (true) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}
			if (!line.startsWith("#")) {
				width = Math.max(width, line.length());
			}
		}
		return width;
	}


	public void addSprite(TileMap map,
        Sprite hostSprite, int tileX, int tileY)
    {
        if (hostSprite != null) {
            mapLoaderProduct4.map(map, hostSprite, tileX, tileY);
        }
    }


	


	


    // -----------------------------------------------------------
    // code for loading sprites and images
    // -----------------------------------------------------------


    public void loadTileImages()
    {
        mapLoaderProduct4.getMapLoaderProduct().loadTileImages();
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void loadCreatureSprites() 
    {

        Image[][] images = mapLoaderProduct4.getMapLoaderProduct().images();
		for (int i=0; i<images[0].length; i++) 
        {
            // right-facing images
            images[1][i] = getMirrorImage(images[0][i]);
            // left-facing "dead" images
            images[2][i] = getFlippedImage(images[0][i]);
            // right-facing "dead" images
            images[3][i] = getFlippedImage(images[1][i]);
        }

        // create creature animations
        Animation[] playerAnim = new Animation[4];
        Animation[] flyAnim = new Animation[4];
        Animation[] grubAnim = new Animation[4];
        
        for (int i=0; i<4; i++) 
        {
            playerAnim[i] = createPlayerAnim (images[i][0]);
            flyAnim[i] = createFlyAnim (images[i][1], images[i][1], images[i][3]);
            grubAnim[i] = createGrubAnim (images[i][4], images[i][5]);
        }

        // create creature sprites
        mapLoaderProduct4.getMapLoaderProduct3().setPlayerSprite(new Player(playerAnim[0], playerAnim[1], playerAnim[2], playerAnim[3]));
        mapLoaderProduct4.setFlySprite(new Fly(flyAnim[0], flyAnim[1], flyAnim[2], flyAnim[3]));
        mapLoaderProduct4.setGrubSprite(new Grub(grubAnim[0], grubAnim[1], grubAnim[2], grubAnim[3]));
    }


	private Animation createPlayerAnim(Image player)
    {
        Animation anim = new Animation();
        anim.addFrame(player, 250);
     
        return anim;
    }


    private Animation createFlyAnim(Image img1, Image img2, Image img3)
    {
        Animation anim = new Animation();
        anim.addFrame(img1, 50);
        anim.addFrame(img2, 50);
        anim.addFrame(img3, 50);
        anim.addFrame(img2, 50);
        return anim;
    }


    private Animation createGrubAnim(Image img1, Image img2)
    {
        Animation anim = new Animation();
        anim.addFrame(img1, 250);
        anim.addFrame(img2, 250);
        return anim;
    }

}
