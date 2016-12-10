package menu;

import java.awt.Color;
import java.awt.Font;

import com.sun.glass.events.KeyEvent;

import controller.InputHandler;
import graphics.Screen;
import main.Main;

public class GoldMenu extends Menu {

	Font defFont;
	int itemSelected;

	public GoldMenu() {
		defFont = new Font("Garamond", 1, 50);
		itemSelected = 0;
	}

	public void update() {
		if (InputHandler.isKeyTyped(KeyEvent.VK_B)) {
			Main.getInstance().inMenu = false;
			Main.getInstance().state = Main.State.GAME;
		}
	}

	public void render(Screen screen) {
		screen.drawRect(Main.getInstance().WINDOWX / 2 - 100, Main.getInstance().WINDOWY / 2 - 100, 200, 200, 0x000000);
		screen.drawString("Gold Shop", 100, 40, defFont, Color.YELLOW);
		// for (int i = 0; i < 2; i++) {
		// if (selectedCharacter[i] > 0)
		// screen.drawString("<", 30 + (960 / 2) * i, 230, arrowFont,
		// Color.BLACK);
		// if (selectedCharacter[i] < characters.size() - 1)
		// screen.drawString(">", 410 + (960 / 2) * i, 230, arrowFont,
		// Color.BLACK);
		// }
		
		Main.getInstance().level.gui(screen);

	}

	public void reset() {

	}

}
