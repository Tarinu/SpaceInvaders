package com.tari.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Player extends Object {
    
    private Vector2 speed;
    private long time = 0;
    private boolean isAlive = true;
    
    public Player(float x, float y, int speed) {
        super(x, y, 20, 20);
        this.speed = new Vector2(speed, 0);
    }
    
    @Override
    public void update(){
        if(getX() >= 280){
            position.x = 280;
        }
        if(getX() <= 0){
            position.x = 0;
        }
        super.update();
    }
    
    public void moveRight(float delta){
        position.add(speed.cpy().scl(delta));
    }
    
    public void moveLeft(float delta){
        position.add(speed.cpy().scl(-delta));
    }
    
    public void shoot(Bullet bullet){
        //Make it so that player can fire once every sec, limits spamming
        if(System.currentTimeMillis() - time > 1000) {
            bullet.shoot(getX() + width / 2, this);
            time = System.currentTimeMillis();
        }
    }
    
    public boolean isAlive() {
        return isAlive;
    }
    
    public void setDead() {
        isAlive = false;
    }
}
