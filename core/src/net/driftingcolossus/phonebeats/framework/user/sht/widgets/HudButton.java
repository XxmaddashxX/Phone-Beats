package net.driftingcolossus.phonebeats.framework.user.sht.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import net.driftingcolossus.phonebeats.framework.graphics.Textures;
import net.driftingcolossus.phonebeats.framework.user.sht.HudComposite;
import net.driftingcolossus.phonebeats.framework.user.sht.HudWidget;
import net.driftingcolossus.phonebeats.framework.user.sht.SHT;

public class HudButton extends HudWidget{

	private Texture button_background_texture;

	private Texture button_background_rollover_texture;

	private Color button_background_color;

	private Color button_font_color;

	private Color button_border_color;

	private boolean button_rollover;

	private boolean button_drawBorders;

	private boolean button_fill;

	private boolean button_useStandardShader;
	
	private boolean button_useRolloverShader;
	
	private boolean button_isDown;
	
	private ShapeRenderer button_renderer;

	private String button_text;

	private BitmapFont button_font;

	private GlyphLayout button_glyphlayout;

	private ShaderProgram button_shader_standard;
	
	private ShaderProgram button_shader_rollover;
	
	private Runnable button_touch_action;
	
	public HudButton(HudComposite composite, Texture baseTexture, Texture rollTexture, String style) {
		super(composite, style);
		checkStyle(style);
		button_background_texture = baseTexture;
		button_background_rollover_texture = rollTexture;
		button_border_color = SHT.Constants.BUTTON_DEFAULT_BORDER_COLOR;
		button_background_color = SHT.Constants.BUTTON_DEFAULT_BACKGROUND_COLOR;
		button_font_color = SHT.Constants.BUTTON_DEFAULT_FONT_COLOR;
		setFont(SHT.Constants.BUTTON_DEFAULT_FONT);
		button_renderer = new ShapeRenderer();
		button_useStandardShader = false;
		button_useRolloverShader = false;
		button_isDown = true;
		button_touch_action  = null;
		//shader = new ShaderProgram(Gdx.files.internal("bin/shaders/edgeglow.vsh"), Gdx.files.internal("bin/shaders/edgeglow.fsh"));
		
	}

	private void checkStyle(String style){
		button_drawBorders = style.contains(SHT.BORDER);
		button_fill = style.contains(SHT.FILL);
	}

	@Override
	public void render(SpriteBatch batch) {
		button_renderer.setProjectionMatrix(batch.getProjectionMatrix());
		if(button_fill){
			button_renderer.setColor(button_background_color);
			button_renderer.begin(ShapeType.Filled);
			button_renderer.rect(widget_x, widget_y, widget_width, widget_height);
			button_renderer.end();
		}
		if(button_background_texture != null && !button_rollover){
			batch.draw(button_background_texture, getX(), getY(), getWidth(), getHeight());
		}
		if(button_background_rollover_texture != null && button_rollover){
			batch.draw(button_background_rollover_texture, widget_x, widget_y, widget_width, widget_height);
		}
		if(button_drawBorders){
			button_renderer.setColor(button_border_color);
			button_renderer.begin(ShapeType.Line);
			button_renderer.rect(widget_x, widget_y, widget_width, widget_height);
			button_renderer.end();
		}
		if(button_text != null && button_font != null && button_glyphlayout != null){
			batch.end();
			batch.begin();
			button_font.setColor(button_font_color);
			button_font.draw(batch, button_text, widget_x + ((widget_width / 2) - (button_glyphlayout.width / 2)), (widget_y + button_glyphlayout.height) + ((widget_height / 2) - (button_glyphlayout.height / 2)));
		}
	}

	/*private void shade(SpriteBatch batch){
		shader.begin();
		shader.setUniformf("u_viewportInverse", new Vector2(1f / 800, 1f / 600));
		shader.setUniformf("u_offset", 50);
		shader.setUniformf("u_step", Math.min(1f, 800 / 70f));
		shader.setUniformf("u_color", new Vector3(255/255, 216/255, 0));
		shader.end();
		batch.setShader(shader);
		batch.begin();
		batch.draw(button_background_texture, getX(), getY(), getWidth(), getHeight());
		batch.end();
		batch.setShader(null);
		batch.begin();
	}*/

	@Override
	public void onDispose() {
		button_renderer.dispose();
	}
	@Override
	public void onMouseOver(int x, int y) {
		
	}
	
	@Override
	public void onTouchDown(int button, int x, int y) {
		button_isDown = true;
	}

	@Override
	public void onTouchUp(int button, int x, int y) {
		button_isDown = false;
		if(button_touch_action != null){
			button_touch_action.run();
		}
	}

	public final void setBorderColor(Color color){
		button_border_color = color;
	}
	public final void setFont(BitmapFont font){
		button_font = font;
		if(button_font != null){
			if(button_text != null){
				button_glyphlayout = new GlyphLayout();
				button_glyphlayout.setText(button_font, button_text);
			}	
		}
		else{
			button_font = null;
			button_glyphlayout = null;
		}
	}
	public final void setText(String text){
		button_text = text;
		if(button_font != null){
			button_glyphlayout = new GlyphLayout();
			button_glyphlayout.setText(button_font, button_text);
		}
	}
	public final void setFontColor(Color color){
		button_font_color = color;
	}

	@Override
	public void pack() {
		if(button_background_texture != null){
			setWidth(button_background_texture.getWidth());
			setHeight(button_background_texture.getHeight());
		}
	}
	public final boolean isDown(){
		return button_isDown;
	}
	public final boolean isUp(){
		return !button_isDown;
	}
	public final void setButtonAction(Runnable runnable){
		button_touch_action = runnable;
	}
	public final ShaderProgram getStandardShader(){
		return button_shader_standard;
	}
	public final ShaderProgram getRolloverShader(){
		return button_shader_rollover;
	}






}
