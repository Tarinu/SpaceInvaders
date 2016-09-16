package com.tari.gameworld;

import com.badlogic.gdx.Gdx;
import com.tari.gameobjects.*;

public class GameWorld {
    private Player player;
    private MobHandler mobHandler;
    private BulletHandler bulletHandler;
    
    private GameState gameState = GameState.GAMEOVER;
    
    public enum GameState {
        RUNNING, GAMEOVER
    }
    
    public GameWorld(){
        player = new Player(200, 350, 150);
        mobHandler = new MobHandler(this);
        bulletHandler = new BulletHandler();
    }
    
    public void update(float delta){
        switch (gameState){
            case RUNNING:
                updateRunning(delta);
                break;
            case GAMEOVER:
                
        }
    }
    
    public void updateRunning(float delta){
        if(player.isAlive()) {
            player.update();
        }
        mobHandler.update();
        bulletHandler.update(delta);
        //Collision test
        for(Bullet bullet: bulletHandler.getBullets()){
            if(!bullet.isAvailable()){
                for(Mob mob: mobHandler.getMobs()){
                    if(mob.isAlive() && bullet.collides(mob)){
                        Gdx.app.log("mob", "dead");
                        mob.setDead();
                        bullet.setAvailable();
                    }
                }
                if(player.isAlive() && bullet.collides(player)){
                    Gdx.app.log("player", "dead");
                    player.setDead();
                    bullet.setAvailable();
                    gameState = GameState.GAMEOVER;
                }
            }
        }
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public MobHandler getMobHandler() {
        return mobHandler;
    }
    
    public BulletHandler getBulletHandler() {
        return bulletHandler;
    }
    
    public boolean isRunning(){
        return gameState == GameState.RUNNING;
    }
    
    public void start(){
        gameState = GameState.RUNNING;
        player = new Player(200, 350, 150);
        mobHandler = new MobHandler(this);
        bulletHandler = new BulletHandler();
    }
}
