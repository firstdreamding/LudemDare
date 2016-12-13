package menu;

import com.sun.glass.events.KeyEvent;

import controller.InputHandler;
import graphics.Screen;
import graphics.Texture;
import main.Main;
import main.SoundPlayer;

public class StartMenu extends Menu {
	Texture bg;
	static SoundPlayer soundPlayer;
	int selected;
	public boolean soundOn;

	public StartMenu(boolean play) {
		bg = new Texture("/res/sprites/start.png", 960, 640);
		selected = 0;
		if (play) {
			soundPlayer = new SoundPlayer("awesomeness.wav");
			soundPlayer.loop();
		}
	}

	public void update() {
		if (InputHandler.isKeyTyped(KeyEvent.VK_S)) {
			bg = new Texture("/res/sprites/start3.png", 960, 640);
			selected = 1;
		}
		if (InputHandler.isKeyTyped(KeyEvent.VK_W)) {
			bg = new Texture("/res/sprites/start2.png", 960, 640);
			selected = 0;
		} else if (InputHandler.isKeyPressed(KeyEvent.VK_ENTER))
			selected();
	}

	private void selected() {
		if (selected == 0) {
			Main.getInstance().state = Main.State.GAME;
			soundPlayer.stop();
		} else
			Main.getInstance().start = new CreditMenu();

	}

	public void render(Screen screen) {
		screen.drawTexture(0, 0, bg);
	}
}
