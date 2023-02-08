package com.TETOSOFT.graphics;

import java.awt.Image;

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
}
