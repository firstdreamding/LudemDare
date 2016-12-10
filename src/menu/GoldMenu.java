package menu;

import java.awt.Color;
import java.awt.Font;

import com.sun.glass.events.KeyEvent;

import controller.InputHandler;
import graphics.Screen;
import main.Main;

public class GoldMenu extends Menu {

	Font defFont, arrowFont;
	int itemSelected;

	public GoldMenu() {
		defFont = new Font("Garamond", 1, 50);
		defFont = new Font("Garamond", 1, 100);
		itemSelected = 2;
	}

	public void update() {
		if (InputHandler.isKeyTyped(KeyEvent.VK_B)) {
			Main.getInstance().inMenu = false;
			Main.getInstance().state = Main.State.GAME;
		}
	}

	public void render(Screen screen) {
		screen.drawRect(Main.getInstance().WINDOWX / 2 - 78, Main.getInstance().WINDOWY / 2 - 100, 200, 200, 0x000000);
		screen.drawString("Gold Shop", 100, 140, defFont, Color.YELLOW);
		 if (itemSelected > 0)
		 screen.drawString("<", Main.getInstance().WINDOWX/2 - 150, Main.getInstance().WINDOWY/2 + 40, arrowFont, Color.BLACK);
		 if (itemSelected < 5)
		 screen.drawString(">", Main.getInstance().WINDOWX/2 + 150, Main.getInstance().WINDOWY/2 + 40, arrowFont, Color.BLACK);
		
		Main.getInstance().level.gui(screen);

	}

	public void reset() {

	}

}
