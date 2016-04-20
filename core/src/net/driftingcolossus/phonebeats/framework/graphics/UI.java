package net.driftingcolossus.phonebeats.framework.graphics;

import javax.xml.parsers.DocumentBuilderFactory;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import net.driftingcolossus.phonebeats.framework.Screen;
import net.driftingcolossus.phonebeats.framework.Strings;
import net.driftingcolossus.phonebeats.framework.user.sht.HudComposite;
import net.driftingcolossus.phonebeats.framework.user.sht.HudShell;
import net.driftingcolossus.phonebeats.framework.user.sht.SHT;
import net.driftingcolossus.phonebeats.framework.user.sht.widgets.HudButton;
import net.driftingcolossus.phonebeats.framework.user.sht.widgets.HudImage;

public class UI {

	public static HudShell mainmenu_shell;
	
	public static HudComposite mainmenu_composite;

	private static HudImage mainmenu_background_image;

	private static HudButton mainmenu_button_play;
	
	
	public static final void newMainMenuScreen(){
		mainmenu_shell = new HudShell(SHT.NODRAW);
		mainmenu_shell.set(0, 0);
		mainmenu_shell.setSize(Screen.SCREEN_VIEWPORT_WIDTH, Screen.SCREEN_VIEWPORT_HEIGHT);
		{
			mainmenu_composite = new HudComposite(mainmenu_shell, SHT.STANDARD);
			mainmenu_background_image = new HudImage(mainmenu_composite, SHT.TRANSITION + SHT.FIT + SHT.FADE);
			mainmenu_background_image.setTextures(new Texture[]{Textures.getTexture("back_01"),Textures.getTexture("back_02"),Textures.getTexture("back_03"),
					Textures.getTexture("back_04"),Textures.getTexture("back_05"),Textures.getTexture("back_06"),Textures.getTexture("back_07"),
					Textures.getTexture("back_08"),Textures.getTexture("back_09"),Textures.getTexture("back_10"),Textures.getTexture("back_11")});
			mainmenu_background_image.setBounds(0, 0, 800, 600);
			mainmenu_background_image.setFadeDuration(1000);
			
			mainmenu_button_play = new HudButton(mainmenu_composite, Textures.getTexture("button_play"), null, SHT.NONE);
			mainmenu_button_play.setBounds(100, 100, 96, 96);
			mainmenu_button_play.setFont(Fonts.get("Dual-300_24"));
			mainmenu_button_play.setFontColor(Color.BLACK);
			mainmenu_button_play.setText(Strings.getString("standard", "play"));
		}
		mainmenu_shell.open();
		
	}
	
}
