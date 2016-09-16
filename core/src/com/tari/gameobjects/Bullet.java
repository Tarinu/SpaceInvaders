package com.tari.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends Object {
    
    private Object owner;
    private boolean available; //boolean for reusing purposes
    private Vector2 velocity;
    
    public Bullet(int speed) {
        super(0, 0, 5, 5);
        available = true;
        velocity = new Vector2(0, speed);
    }
    
    public void update(float delta){
        if(owner instanceof Player) {
            position.add(velocity.cpy().scl(delta));
        }
        else{
            position.add(velocity.cpy().scl(-delta));
        }
        if(position.y < 0 || position.y > 400){
            available = true;
        }
        super.update();
    }
    
    public boolean isAvailable() {
        return available;
    }
    
    public void setAvailable(){
        position.x = 0;
        position.y = 0;
        available = true;
    }
    
    public void shoot(float x, Object owner){
        available = false;
        this.owner = owner;
        position.x = x;
        if(owner instanceof Player){
            position.y = owner.getY() - height;
        }
        else{
            position.y = owner.getY() + height;
        }
    }
    
    public boolean collides(Object object){
        if(owner instanceof Player){
            if(object instanceof Mob){
                return Intersector.overlaps(object.getHitbox(), hitbox);
            }
        }
        if(owner instanceof Mob){
            if(object instanceof Player){
                return Intersector.overlaps(object.getHitbox(), hitbox);
            }
        }
        return false;
    }
}
