package net.driftingcolossus.phonebeats.framework.user.sht.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.driftingcolossus.phonebeats.framework.user.sht.HudComposite;
import net.driftingcolossus.phonebeats.framework.user.sht.HudWidget;
import net.driftingcolossus.phonebeats.framework.user.sht.SHT;

public class HudImage extends HudWidget{

	private Texture[] image_textures;
	
	private boolean image_transition;
	
	private boolean image_fitToSize;
	
	private int image_transition_speed;
	
	private int image_array_selection;
	
	public HudImage(HudComposite composite, String style) {
		super(composite, style);
		checkStyle(style);
		image_array_selection = 0;
	}
	private void checkStyle(String style){
		if(style.contains(SHT.STANDARD)){
			
		}
		image_transition = style.contains(SHT.TRANSITION);
		image_fitToSize = style.contains(SHT.FIT);
	}
	
	@Override
	protected void render(SpriteBatch batch) {
		if(image_textures != null){
			if(!image_transition){
				batch.draw(image_textures[0], widget_x, widget_y, image_fitToSize ? widget_width: image_textures[0].getWidth(), image_fitToSize ? widget_height: image_textures[0].getHeight());
			}
			else{
				Color beforeColor = batch.getColor();
				Color newColor = new Color(beforeColor.r,beforeColor.g, beforeColor.b, beforeColor.a);
				batch.setColor(newColor);
				batch.draw(image_textures[image_array_selection], widget_x, widget_y, image_fitToSize ? widget_width: image_textures[0].getWidth(), image_fitToSize ? widget_height: image_textures[0].getHeight());
				batch.setColor(beforeColor);
			}
		}
	}
	
	@Override
	public void onUpdateTick(int tick) {
		
	}
	public void setTextures(Texture[] array){
		image_textures = array;
	}
	

}
