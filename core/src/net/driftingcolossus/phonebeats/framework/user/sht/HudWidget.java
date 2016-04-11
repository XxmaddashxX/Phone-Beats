package net.driftingcolossus.phonebeats.framework.user.sht;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HudWidget extends HudResource{
	
	
	public float widget_x;
	
	public float widget_y;
	
	public float widget_width;
	
	public float widget_height;
	
	private HudComposite widget_composite;
	
	public HudWidget(HudComposite composite){
		widget_composite = composite;
		widget_x = composite.getShell().getClientArea().x;
		widget_y = composite.getShell().getClientArea().y;
		widget_width = 0;
		widget_height = 0;
		composite.add(this);
	}
	protected void render(SpriteBatch batch){
		
	}
	public final void setBounds(float x, float y, float width, float height){
		widget_x = widget_composite.getShell().getClientArea().x + x;
		widget_y = widget_composite.getShell().getClientArea().y + y;
		widget_width = width;
		widget_height = height;
	}
	public final void translate(float x, float y){
		widget_x += x;
		widget_y += y;
	}
	public final void setWidth(float width){
		widget_width = width;
	}
	public final void setHeight(float height){
		widget_height = height;
	}
	public final void focus(){
		SHT.focusWidget(this);
	}
	public final void unFocus(){
		SHT.unFocusWidget(this);
	}
	
	

}
