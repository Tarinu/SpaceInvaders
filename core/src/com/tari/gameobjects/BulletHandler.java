package com.tari.gameobjects;

import java.util.ArrayList;
import java.util.List;

public class BulletHandler {
    
    private static final int SPEED = -150;
    private List<Bullet> bullets = new ArrayList<Bullet>();
    
    public BulletHandler(){
        for(int i = 0; i < 15; i++){
            bullets.add(new Bullet(SPEED));
        }
    }
    
    public void update(float delta){
        for(Bullet bullet: bullets){
            if(!bullet.isAvailable()){
                bullet.update(delta);
            }
        }
    }
    
    public List<Bullet> getBullets() {
        return bullets;
    }
}
