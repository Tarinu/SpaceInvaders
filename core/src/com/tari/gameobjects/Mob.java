package com.tari.gameobjects;

public class Mob extends Object {
    
    private boolean goRight = true;
    private boolean isAlive = true;
    
    public Mob(float x, float y, int width, int height) {
        super(x, y, width, height);
    }
    
    @Override
    public void update(){
        if(goRight){
            position.x += 5;
        }
        if(!goRight){
            position.x -=5;
        }
        super.update();
    }
    
    public void shoot(Bullet bullet){
        bullet.shoot(getX() + width/2, this);
    }
    
    public void setGoRight(boolean goRight) {
        this.goRight = goRight;
    }
    
    public boolean isGoRight() {
        return goRight;
    }
    
    public void goLower(){
        position.y += 30;
    }
    
    public boolean isAlive() {
        return isAlive;
    }
    
    public void setDead(){
        isAlive = false;
    }
}
