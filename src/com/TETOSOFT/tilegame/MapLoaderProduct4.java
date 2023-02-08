package com.TETOSOFT.tilegame;


import com.TETOSOFT.graphics.Sprite;
import com.TETOSOFT.graphics.Animation;
import com.TETOSOFT.tilegame.sprites.PowerUp;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.Image;

public class MapLoaderProduct4 {
	private MapLoaderProduct3 mapLoaderProduct3 = new MapLoaderProduct3();
	private MapLoaderProduct mapLoaderProduct = new MapLoaderProduct();
	private Sprite musicSprite;
	private Sprite coinSprite;
	private Sprite goalSprite;
	private Sprite grubSprite;
	private Sprite flySprite;

	public MapLoaderProduct3 getMapLoaderProduct3() {
		return mapLoaderProduct3;
	}

	public MapLoaderProduct getMapLoaderProduct() {
		return mapLoaderProduct;
	}

	public void setGrubSprite(Sprite grubSprite) {
		this.grubSprite = grubSprite;
	}

	public void setFlySprite(Sprite flySprite) {
		this.flySprite = flySprite;
	}

	public void map(TileMap map, Sprite hostSprite, int tileX, int tileY) {
		Sprite sprite = mapLoaderProduct3.sprite(hostSprite, tileX, tileY);
		map.addSprite(sprite);
	}

	public void loadPowerUpSprites() {
		Animation anim = new Animation();
		anim.addFrame(mapLoaderProduct.loadImage("heart.png"), 150);
		goalSprite = new PowerUp.Goal(anim);
		anim = new Animation();
		anim.addFrame(mapLoaderProduct.loadImage("coin1.png"), 250);
		anim.addFrame(mapLoaderProduct.loadImage("coin2.png"), 250);
		anim.addFrame(mapLoaderProduct.loadImage("coin3.png"), 250);
		anim.addFrame(mapLoaderProduct.loadImage("coin4.png"), 250);
		anim.addFrame(mapLoaderProduct.loadImage("coin5.png"), 250);
		coinSprite = new PowerUp.Star(anim);
		anim = new Animation();
		anim.addFrame(mapLoaderProduct.loadImage("music1.png"), 150);
		anim.addFrame(mapLoaderProduct.loadImage("music2.png"), 150);
		anim.addFrame(mapLoaderProduct.loadImage("music3.png"), 150);
		anim.addFrame(mapLoaderProduct.loadImage("music2.png"), 150);
		musicSprite = new PowerUp.Music(anim);
		musicSprite = new PowerUp.Music(anim);
	}

	public TileMap loadMap(String filename, MapLoader mapLoader) throws IOException {
		ArrayList lines = new ArrayList();
		int width = mapLoader.width(filename);
		int height = 0;
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		while (true) {
			String line = reader.readLine();
			if (line == null) {
				reader.close();
				break;
			}
			if (!line.startsWith("#")) {
				lines.add(line);
			}
		}
		height = lines.size();
		TileMap newMap = new TileMap(width, height);
		for (int y = 0; y < height; y++) {
			String line = (String) lines.get(y);
			for (int x = 0; x < line.length(); x++) {
				char ch = line.charAt(x);
				int tile = ch - 'A';
				if (tile >= 0 && tile < mapLoaderProduct.getTiles().size()) {
					newMap.setTile(x, y, (Image) mapLoaderProduct.getTiles().get(tile));
				} else if (ch == 'o') {
					mapLoader.addSprite(newMap, coinSprite, x, y);
				} else if (ch == '!') {
					mapLoader.addSprite(newMap, musicSprite, x, y);
				} else if (ch == '*') {
					mapLoader.addSprite(newMap, goalSprite, x, y);
				} else if (ch == '1') {
					mapLoader.addSprite(newMap, grubSprite, x, y);
				} else if (ch == '2') {
					mapLoader.addSprite(newMap, flySprite, x, y);
				}
			}
		}
		Sprite player = mapLoaderProduct3.player(lines);
		newMap.setPlayer(player);
		return newMap;
	}
}