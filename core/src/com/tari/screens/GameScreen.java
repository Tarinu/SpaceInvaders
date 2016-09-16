package com.tari.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.tari.gameworld.GameRenderer;
import com.tari.gameworld.GameWorld;
import com.tari.input.InputHandler;

public class GameScreen implements Screen{
    private GameWorld gameWorld;
    private GameRenderer gameRenderer;
    private InputHandler inputHandler;
    
    public GameScreen(){
        Gdx.app.log("GameScreen", "Attached");
        gameWorld = new GameWorld();
        gameRenderer = new GameRenderer(gameWorld);
        
        inputHandler = new InputHandler(gameWorld);
        Gdx.input.setInputProcessor(inputHandler);
    }
    
    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show");
    }
    
    @Override
    public void render(float delta) {
        gameWorld.update(delta);
        if(inputHandler.akeyPressed) gameWorld.getPlayer().moveLeft(delta);
        if(inputHandler.dkeyPressed) gameWorld.getPlayer().moveRight(delta);
        gameRenderer.render();
    }
    
    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resize");
    }
    
    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause");
    }
    
    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume");
    }
    
    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide");
    }
    
    @Override
    public void dispose() {
        Gdx.app.log("GameScreen", "dispose");
    }
}
