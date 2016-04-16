package net.driftingcolossus.phonebeats.framework.user.sht.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.driftingcolossus.phonebeats.framework.user.sht.HudComposite;
import net.driftingcolossus.phonebeats.framework.user.sht.HudWidget;

public class HudButton extends HudWidget{

	private Texture button_background_texture;
	
	private Texture button_background_rollover_texture;
	
	private Color button_background_color;
	
	private Color button_font_color;
	
	private boolean button_rollover;
	
	public HudButton(HudComposite composite, Texture texture, String style) {
		super(composite, style);
	}

	@Override
	public void render(SpriteBatch batch) {
		if(button_background_texture != null){
			batch.draw(button_background_texture, getX(), getY(), getWidth(), getHeight());
		}
	}
	
	
	

}
