package main;

import controller.HitboxController;
import controller.InputHandler;
import entities.Player;
import graphics.Screen;
import graphics.Texture;
import graphics.Window;

public class Main {

	public Main() throws Exception {
		instance = this;
	}

	ClassLoader cl = getClass().getClassLoader();

	Level level;
	
	long tick, timeLR;
	double fps;
	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	private void init() {
		level = new Level();
		tick = 0;
		fps = 1000 / 60;
		timeLR = System.currentTimeMillis();

	}
	private void loop() throws InterruptedException {
		Window window = new Window("Game", 960, 540);
		window.show();
		Screen screen = window.getScreen();
		window.addKeyListener(new InputHandler());

		while (true) {
			if ((double) (System.currentTimeMillis() - timeLR) > fps) {
				screen.clear(255255255);
				level.update(screen);
				window.update();
				timeLR = System.currentTimeMillis();
				tick++;
				InputHandler.clear();
			}
		}
	}

	public static void main(String[] args) throws Exception {

		Main game = new Main();
		game.init();
		game.loop();

	}

}