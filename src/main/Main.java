package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

	public State state = State.GAME;
	public boolean inMenu = false;
	ClassLoader cl = getClass().getClassLoader();
	public Player player;
	Texture bg;
	public Level level;
	HitboxController hbc;
	Menu menu = new GoldMenu();
	public long tick;
	long timeLR;
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

	int tempx, tempy = 0;

	private void loop() throws InterruptedException {
		Window window = new Window("Game", 960, 640);
		window.show();

		window.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				if (e.getButton() == MouseEvent.BUTTON1) {
					tempx = e.getX();
					tempy = e.getY();
					System.out.println(tempx + "," + tempy);
				} else if (e.getButton() == MouseEvent.BUTTON3) {
					System.out.println("Wdidth,Height: " + (e.getX() - tempx) + "," + (e.getY() - tempy));
				}
			}
		});
		Screen screen = window.getScreen();
		window.addKeyListener(new InputHandler());

		while (true) {
			if ((double) (System.currentTimeMillis() - timeLR) > fps) {
				if (state.equals(State.GAME)) {
					level.update(screen);
				} else if (state.equals(State.MENU)) {
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