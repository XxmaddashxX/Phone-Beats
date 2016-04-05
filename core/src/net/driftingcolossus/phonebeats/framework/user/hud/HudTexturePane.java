package net.driftingcolossus.phonebeats.framework.user.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HudTexturePane extends HudComponent{

	private static final String COMPONENT_NAME = "Hud Texture Pane";
	
	private boolean isRolledOver;
	
	private Texture pane_texture_normal;
	
	private Texture pane_texture_rollover;
	
	public HudTexturePane(String idName, float x, float y, float width, float height, Texture normalTexture, Texture rolloverTexture) {
		super(idName, COMPONENT_NAME, x, y, width, height);
		pane_texture_normal = normalTexture;
		pane_texture_rollover = rolloverTexture;
	}

	public HudTexturePane(String idName, float x, float y, Texture normalTexture, Texture rolloverTexture) {
		super(idName, COMPONENT_NAME, x, y);
		float maxWidth = normalTexture.getWidth();
		float maxHeight = normalTexture.getHeight();
		if(rolloverTexture != null){
			maxWidth = Math.max(normalTexture.getWidth(), rolloverTexture.getWidth());
			maxHeight = Math.max(normalTexture.getHeight(), rolloverTexture.getHeight());
		}
		setWidth(maxWidth);
		setHeight(maxHeight);
		pane_texture_normal = normalTexture;
		pane_texture_rollover = rolloverTexture;
		this.updateControlNodes();
	}

	@Override
	public void onRenderEvent(SpriteBatch spritebatch, ShapeRenderer fillrenderer, ShapeRenderer linerenderer) {
		if(isRolledOver && pane_texture_rollover != null){
			spritebatch.draw(pane_texture_rollover, getX(), getY(), getWidth(), getHeight());
		}
		else{
			spritebatch.draw(pane_texture_normal, getX(), getY(), getWidth(), getHeight());
		}
	}

	@Override
	public void onMouseOver(int componentX, int componentY) {
		isRolledOver = true;
	}

	@Override
	public void onMouseOff() {
		isRolledOver = false;
	}
	
	

	 
	

}
