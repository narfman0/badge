package com.blastedstudios.badge;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.blastedstudios.badge.client.ui.MainScreen;

public class Badge extends Game {
	@Override public void create () {
		setScreen(new MainScreen(this));
	}
	
	public static void main (String[] argv) {
		new LwjglApplication(new Badge(), "Badge", 1024, 768, false);
	}
}
