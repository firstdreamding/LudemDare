package main;

import controller.HitboxController;
import controller.InputHandler;
import entities.Player;
import graphics.Screen;
import graphics.Texture;
import graphics.Window;
import menu.GoldMenu;
import menu.Menu;

public class Main {

	public Main() throws Exception {
		instance = this;
	}

	public enum State {
		MENU, GAME;
	};

	State state = State.MENU;
	ClassLoader cl = getClass().getClassLoader();
	Player player;
	Texture bg;
	Level level;
	HitboxController hbc;
	Menu menu = new GoldMenu();
	long tick, timeLR;
	double fps;
	public static Main instance;
	public final int WINDOWX = 960;
	public final int WINDOWY = 640;

	public static Main getInstance() {
		return instance;
	}

	private void init() {
		level = new Level();
		bg = new Texture("/res/sprites/bg.png", WINDOWX, WINDOWY);
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
					}
				else if (state.equals(State.MENU)){
					menu.render(screen);
					menu.update();
				}
				window.update();
				screen.clear(0xffffff);
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