package com.TETOSOFT.tilegame;


import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.Transparency;
import java.awt.Graphics2D;

public class MapLoaderProduct2 {
	private GraphicsConfiguration gc;

	public void setGc(GraphicsConfiguration gc) {
		this.gc = gc;
	}

	public Image getScaledImage(Image image, float x, float y) {
		AffineTransform transform = transform(image, x, y);
		Image newImage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), Transparency.BITMASK);
		Graphics2D g = (Graphics2D) newImage.getGraphics();
		g.drawImage(image, transform, null);
		g.dispose();
		return newImage;
	}

	public AffineTransform transform(Image image, float x, float y) {
		AffineTransform transform = new AffineTransform();
		transform.scale(x, y);
		transform.translate((x - 1) * image.getWidth(null) / 2, (y - 1) * image.getHeight(null) / 2);
		return transform;
	}
}