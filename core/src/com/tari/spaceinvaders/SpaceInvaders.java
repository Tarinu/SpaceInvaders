package com.tari.spaceinvaders;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.tari.screens.GameScreen;

public class SpaceInvaders extends Game {
	
	@Override
	public void create() {
		Gdx.app.log("SI", "created");
		setScreen(new GameScreen());
	}
}
