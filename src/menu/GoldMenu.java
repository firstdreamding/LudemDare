package menu;

import java.awt.Color;
import java.awt.Font;

import graphics.Screen;
import main.Main;

public class GoldMenu extends Menu {
	
	Font defFont;
	
	public GoldMenu(){
		defFont = new Font("Garamond", 1, 50);
	}
	
	public void update() {
	}

	public void render(Screen screen) {
		screen.drawRect(Main.getInstance().WINDOWX/2-100, Main.getInstance().WINDOWY/2-100, 200, 200, 0x000000);
		screen.drawString("Gold Shop", 100, 40, defFont, Color.YELLOW);
	}

	public void reset() {

	}

}
