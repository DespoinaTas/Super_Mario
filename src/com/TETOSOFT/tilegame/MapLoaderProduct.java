package com.TETOSOFT.tilegame;


import java.util.ArrayList;
import java.io.File;
import java.awt.Image;
import javax.swing.ImageIcon;

public class MapLoaderProduct {
	private ArrayList tiles;

	public ArrayList getTiles() {
		return tiles;
	}

	public void loadTileImages() {
		tiles = new ArrayList();
		char ch = 'A';
		while (true) {
			String name = ch + ".png";
			File file = new File("images/" + name);
			if (!file.exists())
				break;
			tiles.add(loadImage(name));
			ch++;
		}
	}

	/**
	* Gets an image from the images/ directory.
	*/
	public Image loadImage(String name) {
		String filename = "images/" + name;
		return new ImageIcon(filename).getImage();
	}

	public Image[][] images() {
		Image[][] images = new Image[4][];
		images[0] = new Image[] { loadImage("player.png"), loadImage("fly1.png"), loadImage("fly2.png"),
				loadImage("fly3.png"), loadImage("grub1.png"), loadImage("grub2.png") };
		images[1] = new Image[images[0].length];
		images[2] = new Image[images[0].length];
		images[3] = new Image[images[0].length];
		return images;
	}
}