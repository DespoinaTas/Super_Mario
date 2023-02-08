package com.TETOSOFT.graphics;


public class SpriteProduct implements Cloneable {
	private float x;
	private float y;
	private float dx;
	private float dy;

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getDx() {
		return dx;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public float getDy() {
		return dy;
	}

	public void setDy(float dy) {
		this.dy = dy;
	}

	public void update(long elapsedTime, Animation thisAnim) {
		x += dx * elapsedTime;
		y += dy * elapsedTime;
		thisAnim.update(elapsedTime);
	}

	public Object clone() {
		try {
			return (SpriteProduct) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e);
		}
	}
}