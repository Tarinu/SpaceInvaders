package com.tari.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Object {
    protected Vector2 position;
    protected int width;
    protected int height;
    protected Rectangle hitbox;
    
    public Object(float x, float y, int width, int height){
        position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        hitbox = new Rectangle(x, y, width, height);
    }
    
    public void update(){
        hitbox.set(position.x, position.y, width, height);
    }
    
    public float getX(){
        return position.x;
    }
    
    public float getY(){
        return position.y;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public Rectangle getHitbox() {
        return hitbox;
    }
}
