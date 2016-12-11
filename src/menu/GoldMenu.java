package menu;

import java.awt.Color;
import java.awt.Font;

import com.sun.glass.events.KeyEvent;

import controller.InputHandler;
import graphics.Screen;
import items.Item;
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
		
		if (InputHandler.isKeyTyped(KeyEvent.VK_A) && itemSelected > 0){ 
			itemSelected--;
		} else if (InputHandler.isKeyTyped(KeyEvent.VK_D) && itemSelected < 2)
			itemSelected++;
	}

	public void render(Screen screen) {
		screen.drawRect(Main.getInstance().WINDOWX / 2 - 100, Main.getInstance().WINDOWY / 2 - 100, 200, 200, 0x000000);
		screen.drawString("Gold Shop", 100, 140, defFont, Color.YELLOW);
		 if (itemSelected > 0)
		 screen.drawString("<", Main.getInstance().WINDOWX/2 - 150, Main.getInstance().WINDOWY/2 + 40, arrowFont, Color.BLACK);
		 if (itemSelected < 5)
		 screen.drawString(">", Main.getInstance().WINDOWX/2 + 150, Main.getInstance().WINDOWY/2 + 40, arrowFont, Color.BLACK);
		
		Main.getInstance().level.gui(screen);
		
		screen.drawTexture(Main.getInstance().WINDOWX / 2 - 100, Main.getInstance().WINDOWY / 2 - 100, Item.getByID(itemSelected).texture);
		screen.drawString(Item.getByID(itemSelected).name,Main.getInstance().WINDOWX / 2 + 200, Main.getInstance().WINDOWY / 2 - 100, defFont, Color.BLACK);

	}

	public void reset() {

	}

}
