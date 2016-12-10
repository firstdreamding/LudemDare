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

	public enum State {
		MENU, GAME;
	};

	State state = State.GAME;
	ClassLoader cl = getClass().getClassLoader();
	Player player;
	Texture bg;
	Level level;
	HitboxController hbc;
	long tick, timeLR;
	double fps;
	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	private void init() {
		level = new Level();
		bg = new Texture("/res/sprites/bg.png", 960, 640);
		tick = 0;
		fps = 1000 / 60;
		timeLR = System.currentTimeMillis();
		

	}

	private void loop() throws InterruptedException {
		Window window = new Window("Game", 960, 640);
		window.show();
		Screen screen = window.getScreen();
		window.addKeyListener(new InputHandler());

		while (true) {
			if ((double) (System.currentTimeMillis() - timeLR) > fps) {
				if (state.equals(State.GAME)) {
					level.update(screen);
					window.update();				
					}
				else if (state.equals(State.MENU)){
					
				}
				screen.clear(255255255);
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