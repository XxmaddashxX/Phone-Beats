package net.driftingcolossus.phonebeats.framework.user.sht.widgets;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.driftingcolossus.phonebeats.framework.user.sht.composites.HudComposite;

public class HudWidget {
	
	private final HashMap<String, Object> widget_data = new HashMap<String, Object>();
	
	public float widget_x;
	
	public float widget_y;
	
	public float widget_width;
	
	public float widget_height;
	
	public HudWidget(HudComposite composite){
		widget_x = 0;
		widget_y = 0;
		widget_width = 0;
		widget_height = 0;
	}
	public void render(SpriteBatch batch){
		
	}

}
