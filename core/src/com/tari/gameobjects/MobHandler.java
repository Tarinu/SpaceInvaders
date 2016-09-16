package com.tari.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.tari.gameworld.GameWorld;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.floor;
import static com.badlogic.gdx.math.MathUtils.random;

public class MobHandler {
    private GameWorld gameWorld;
    private long time = System.currentTimeMillis();
    private List<Mob> mobs = new ArrayList<Mob>();
    private Rectangle zone; //zone where the mobs are in
    
    private static final int VERTICAL_GAP = 40;
    private static final int HORIZONTAL_GAP = 40;
    private static final int STARTING_Y = 120;
    private static final int STARTING_X = 20;
    public static final int SCROLL_SPEED = 250; //Time between enemies moving in millisecs
    
    public MobHandler(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        for(int i = 0; i < 15; i++){
            if(mobs.size() == 0){
                //Add the first one so its possible to calculate the rest from its position
                mobs.add(new Mob(STARTING_X, STARTING_Y, 15, 15));
            }
            else{
                //Calculates so that there are only max 5 mobs in a row
                mobs.add(new Mob(STARTING_X+HORIZONTAL_GAP*i-5*HORIZONTAL_GAP*floor(i/5),
                        STARTING_Y-VERTICAL_GAP*floor(i/5), 15, 15));
            }
        }
        zone = new Rectangle(STARTING_X, STARTING_Y - floor(mobs.size())*VERTICAL_GAP,
                4*(HORIZONTAL_GAP)+15, 3*(VERTICAL_GAP+15));
    }
    
    public void update(){
        //Uses RNG to decide if a mob should shoot or not
        for(Mob mob: mobs){
            if(mob.isAlive()){
                if(random() > 0.999){
                    for(Bullet bullet: gameWorld.getBulletHandler().getBullets()){
                        if(bullet.isAvailable()){
                            //Gdx.app.log("mob", "fires");
                            mob.shoot(bullet);
                            break;
                        }
                    }
                }
            }
        }
        if(System.currentTimeMillis() - time > SCROLL_SPEED){
            if(zone.x == 0){
                for(Mob mob : mobs){
                    mob.setGoRight(true);
                    mob.goLower();
                }
            }
            if(zone.x >= 300-zone.width){
                for(Mob mob : mobs){
                    mob.setGoRight(false);
                    mob.goLower();
                }
            }
            //Gdx.app.log("mob", String.valueOf(mobs.get(4).getX()));
            for(Mob mob : mobs){
                if(mob.isAlive()) {
                    mob.update();
                }
            }
            if(mobs.get(0).isGoRight()){
                zone.x += 5;
            }
            else{
                zone.x -= 5;
            }
            time = System.currentTimeMillis();
        }
    }
    
    public List<Mob> getMobs() {
        return mobs;
    }
    
    public Rectangle getZone() {
        return zone;
    }
}
