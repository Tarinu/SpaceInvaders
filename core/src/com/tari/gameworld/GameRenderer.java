package com.tari.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tari.gameobjects.Bullet;
import com.tari.gameobjects.Mob;

public class GameRenderer {
    
    private GameWorld gameWorld;
    private ShapeRenderer shapeRenderer;
    private FPSLogger fpsLogger;
    
    private SpriteBatch batcher;
    
    public static BitmapFont font;
    
    public GameRenderer(GameWorld gameWorld){
        this.gameWorld = gameWorld;
        OrthographicCamera cam = new OrthographicCamera();
        cam.setToOrtho(true, 300, 400);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        fpsLogger = new FPSLogger();
        
        batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
        
        /*FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Xerox Sans Serif Wide.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 18;
        font = generator.generateFont(parameter);
		generator.dispose();*/
    }
    
    public void render(){
        fpsLogger.log();
        if(gameWorld.isRunning()) {
            //Fill background with black
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
            //Draw the white rectangles(objects)
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(255f, 255f, 255f, 1f);
    
            //Draw player
            //if(gameWorld.getPlayer().isAlive())
            shapeRenderer.rect(gameWorld.getPlayer().getX(), gameWorld.getPlayer().getY(),
                    gameWorld.getPlayer().getWidth(), gameWorld.getPlayer().getHeight());
            
            //Draw the enemies
            for (Mob mob : gameWorld.getMobHandler().getMobs()) {
                if (mob.isAlive()) {
                    shapeRenderer.rect(mob.getX(), mob.getY(),
                            mob.getWidth(), mob.getHeight());
                }
            }
    
            //Draw bullets
            for (Bullet bullet : gameWorld.getBulletHandler().getBullets()) {
                if (!bullet.isAvailable()) {
                    shapeRenderer.rect(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
                }
            }
            shapeRenderer.end();
        }
        else{
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        }
    }
}
