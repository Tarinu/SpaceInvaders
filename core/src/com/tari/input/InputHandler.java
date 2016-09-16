package com.tari.input;

import com.badlogic.gdx.InputProcessor;
import com.tari.gameobjects.Bullet;
import com.tari.gameworld.GameWorld;

import static com.badlogic.gdx.Input.Keys.*;

public class InputHandler implements InputProcessor {
    
    public boolean akeyPressed;
    public boolean dkeyPressed;
    private GameWorld gameWorld;
    
    public InputHandler(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }
    
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case A:
                akeyPressed = true;
                break;
            case LEFT:
                akeyPressed = true;
                break;
            case D:
                dkeyPressed = true;
                break;
            case RIGHT:
                dkeyPressed = true;
                break;
            case SPACE:
                if(!gameWorld.isRunning()){
                    gameWorld.start();
                }
                else {
                    for (Bullet bullet : gameWorld.getBulletHandler().getBullets()) {
                        if (bullet.isAvailable()) {
                            gameWorld.getPlayer().shoot(bullet);
                            break;
                        }
                    }
                }
                break;
        }
        return false;
    }
    
    @Override
    public boolean keyUp(int keycode) {
        if(keycode == A || keycode == LEFT) {
            akeyPressed = false;
        }
        if(keycode == D || keycode == RIGHT){
            dkeyPressed = false;
        }
        return false;
    }
    
    @Override
    public boolean keyTyped(char character) {
        return false;
    }
    
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    
    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
