package net.driftingcolossus.phonebeats.framework.user.sht;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class HudShell{

	private boolean shell_showTitle;

	private boolean shell_showClose;
	
	private boolean shell_showMin;
	
	private boolean shell_showMax;
	
	private boolean shell_showIcon;
	
	private boolean shell_canResize;
	
	private boolean shell_drawBorder;
	
	private boolean shell_isMaximized;
	
	private boolean shell_isMinimized;
	
	private boolean shell_isOpen;
	
	private boolean shell_isVisible;
	
	private boolean shell_isCaptured;
	
	private String shell_title;
	
	private float shell_position_x;
	
	private float shell_position_y;
	
	private float shell_size_width;
	
	private float shell_size_height;
	
	private float shell_size_min_width;
	
	private float shell_size_min_height;
	
	private float shell_size_max_width;
	
	private float shell_size_max_height;
	
	
	public HudShell(String style){
		checkStyle(style);
	}
	public HudShell(String title, String style){
		shell_title = title;
		checkStyle(style);
	}

	private final void checkStyle(String style){
		if(style.contains(SHT.STANDARD)){
			shell_showTitle = true;
			shell_showIcon = true;
			shell_showMin = true;
			shell_showMax = true;
			shell_showClose = true;
		}
		if(style.contains(SHT.TITLE)){
			shell_showTitle = true;
		}
		if(style.contains(SHT.ICON)){
			shell_showIcon = true;
		}
		if(style.contains(SHT.MIN)){
			shell_showMin = true;
		}
		if(style.contains(SHT.MAX)){
			shell_showMax = true;
		}
		if(style.contains(SHT.CLOSE)){
			shell_showClose = true;
		}
		if(style.contains(SHT.RESIZE)){
			shell_canResize = true;
		}
		if(style.contains(SHT.BORDER)){
			shell_drawBorder = true;
		}
	}
	public final void render(){
		
	}
	public final void update(){
		
	}
	static class STATIC{
		
		private static BitmapFont shell_default_font;
		
		private static Color shell_default_color_frame;
		
	}

}
