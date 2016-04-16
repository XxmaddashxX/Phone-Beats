package net.driftingcolossus.phonebeats.framework.graphics;

import net.driftingcolossus.phonebeats.framework.Screen;
import net.driftingcolossus.phonebeats.framework.user.sht.HudShell;
import net.driftingcolossus.phonebeats.framework.user.sht.SHT;

public class UI {

	public static HudShell mainmenu_shell;
	
	public static final void newMainMenuScreen(){
		mainmenu_shell = new HudShell(SHT.NODRAW);
		mainmenu_shell.set(0, 0);
		mainmenu_shell.setSize(Screen.SCREEN_VIEWPORT_WIDTH, Screen.SCREEN_VIEWPORT_HEIGHT);
		mainmenu_shell.open();
		
	}
	
}
