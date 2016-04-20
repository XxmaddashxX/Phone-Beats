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
	
	private boolean image_fade;
	
	private float image_transition_speed;
	
	private int image_array_selection_1;
	
	private int image_array_selection_2;
	
	private float image_tick_counter;
	
	public HudImage(HudComposite composite, String style) {
		super(composite, style);
		checkStyle(style);
		image_array_selection_1 = 0;
		image_array_selection_2 = 1;
		image_tick_counter = 1;
		image_transition_speed = 1000;
	}
	private void checkStyle(String style){
		if(style.contains(SHT.STANDARD)){
			image_fitToSize = true;
			return;
		}
		image_transition = style.contains(SHT.TRANSITION);
		image_fitToSize = style.contains(SHT.FIT);
		image_fade = style.contains(SHT.FADE);
	}
	
	@Override
	protected void render(SpriteBatch batch) {
		if(image_textures != null){
			if(!image_transition){
				batch.draw(image_textures[0], widget_x, widget_y, image_fitToSize ? widget_width: image_textures[0].getWidth(), image_fitToSize ? widget_height: image_textures[0].getHeight());
			}
			else if(image_fade){
				Color beforeColor = batch.getColor();
				Color newColor1 = new Color(beforeColor.r,beforeColor.g, beforeColor.b, (1 / image_transition_speed) * image_tick_counter);
				Color newColor2 = new Color(beforeColor.r,beforeColor.g, beforeColor.b, 1 - newColor1.a);
				batch.setColor(newColor1);
				batch.draw(image_textures[image_array_selection_2], widget_x, widget_y, image_fitToSize ? widget_width: image_textures[image_array_selection_2].getWidth(), image_fitToSize ? widget_height: image_textures[image_array_selection_2].getHeight());
				batch.flush();
				batch.setColor(newColor2);
				batch.draw(image_textures[image_array_selection_1], widget_x, widget_y, image_fitToSize ? widget_width: image_textures[image_array_selection_1].getWidth(), image_fitToSize ? widget_height: image_textures[image_array_selection_1].getHeight());
				batch.setColor(beforeColor);
			}
			else{
				batch.draw(image_textures[image_array_selection_1], widget_x, widget_y, image_fitToSize ? widget_width: image_textures[image_array_selection_1].getWidth(), image_fitToSize ? widget_height: image_textures[image_array_selection_1].getHeight());
			}
		}
		batch.flush();
	}
	
	@Override
	public void onUpdateTick(int tick) {
		image_tick_counter++;
		if(image_tick_counter > image_transition_speed){
			image_tick_counter = 1;
			image_array_selection_1++;
			if(image_array_selection_1 > image_textures.length -1 ){
				image_array_selection_1 = 0;
			}
			image_array_selection_2++;
			if(image_array_selection_2 > image_textures.length - 1){
				image_array_selection_2 = 0;
			}
		}
	}
	public void setTextures(Texture[] array){
		image_textures = array;
	}
	public void setFadeDuration(int amount){
		image_transition_speed = amount;
	}
	

}
