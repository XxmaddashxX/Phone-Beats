package net.driftingcolossus.phonebeats.framework.user.hud;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HudLabel extends HudComponent{

	private static final String COMPONENT_NAME = "Hud Label";
	
	private GlyphLayout label_glyphlayout;
	
	private BitmapFont label_font;
	
	private String label_text;
	
	private int label_pad;
	
	private float label_font_default_width;
	
	private float label_font_default_height;
	
	private float label_float_scaleX;
	
	private float label_float_scaleY;
	
	public HudLabel(String idName, float x, float y, String text, BitmapFont font, int pad) {
		super(idName, COMPONENT_NAME, x, y);
		label_glyphlayout = new GlyphLayout();
		label_font = font;
		label_text = text;
		label_pad = pad;
		label_glyphlayout.setText(label_font, label_text);
		setWidth(label_glyphlayout.width + (label_pad * 2));
		setHeight(label_glyphlayout.height + (label_pad * 2));
		label_font_default_width = label_glyphlayout.width;
		label_font_default_height = label_glyphlayout.height;
		label_float_scaleX = 1;
		label_float_scaleY = 1;
		this.updateControlNodes();
	}

	@Override
	public void onRenderEvent(SpriteBatch spritebatch, ShapeRenderer fillrenderer, ShapeRenderer linerenderer) {
		label_font.draw(spritebatch, label_text, getX() + ((getWidth() / 2) - (label_glyphlayout.width / 2)), getY() + ((getHeight() / 2) - (label_glyphlayout.height / 2)) + label_glyphlayout.height);
	}

	@Override
	public void onComponentResized(float oldWidth, float oldHeight) {
		label_float_scaleX = (getWidth() - (label_pad * 2)) / label_font_default_width;
		label_float_scaleY = (getHeight() - (label_pad * 2)) / label_font_default_height;
		label_glyphlayout.setText(label_font, label_text);
	}

	@Override
	public void onUpdateEvent() {
		label_font.getData().setScale(label_float_scaleX, label_float_scaleY);
	}

	@Override
	public void onDisposeEvent() {
		
	}
	
	
	
	

}
