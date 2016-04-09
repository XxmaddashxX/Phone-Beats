package net.driftingcolossus.phonebeats.framework.user.sht;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class HudShell extends HudResource implements HudDrawable{

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

	private boolean shell_showTitleBar;

	private String shell_title;

	private float shell_position_x;

	private float shell_position_y;

	private float shell_size_width;

	private float shell_size_height;

	private float shell_size_min_width;

	private float shell_size_min_height;

	private float shell_size_max_width;

	private float shell_size_max_height;

	private float shell_border_thickness;

	private float shell_title_thickness;

	private GlyphLayout shell_title_glyphlayout;

	private BitmapFont shell_title_bitmapfont;

	private Color shell_border_color;

	private Color shell_title_font_color;

	private Color shell_background_color;
	
	private Texture shell_icon;

	private Rectangle shell_bounds_shell;

	private Rectangle shell_bounds_client_area;

	private Rectangle shell_bounds_title_area;
	
	private Rectangle shell_bounds_border_left;
	
	private Rectangle shell_bounds_border_right;
	
	private Rectangle shell_bounds_border_bottom;
	
	private Rectangle shell_bounds_border_bottom_left;
	
	private Rectangle shell_bounds_border_bottom_right;
	
	private Rectangle shell_bounds_border_top_left;
	
	private Rectangle shell_bounds_border_top_right;
	
	
	public HudShell(String style){
		checkStyle(style);
		initialize("Hud Shell", STATIC.shell_default_shell_position_x, STATIC.shell_default_shell_position_y, STATIC.shell_default_shell_size_width, STATIC.shell_default_shell_size_height,
				null, null, null, null, null, -1);
	}
	public HudShell(String title, String style){
		shell_title = title;
		checkStyle(style);
		initialize(title, STATIC.shell_default_shell_position_x, STATIC.shell_default_shell_position_y, STATIC.shell_default_shell_size_width, STATIC.shell_default_shell_size_height,
				null, null, null, null, null, -1);
	}
	private final void initialize(String title, float x, float y, float width, float height, BitmapFont font_Title, Color borderColor, Color titleFontColor, Color backgroundColor,
			Texture icon, float borderThickness){
		shell_title = title;
		shell_icon = icon;
		shell_position_x = x;
		shell_position_y = y;
		shell_size_width = width;
		shell_size_height = height;
		if(font_Title != null){
			shell_title_bitmapfont = font_Title;
		}
		else{
			shell_title_bitmapfont = STATIC.shell_default_font;
		}
		shell_title_glyphlayout = new GlyphLayout();
		shell_title_glyphlayout.setText(shell_title_bitmapfont, shell_title);
		if(borderColor != null){
			shell_border_color = borderColor;
		}
		else{
			shell_border_color = STATIC.shell_default_color_frame;
		}
		if(titleFontColor != null){
			shell_title_font_color = titleFontColor;
		}
		else{
			shell_title_font_color = STATIC.shell_default_color_title_font;
		}

		if(shell_showTitle){
			if(shell_showIcon && shell_icon != null && shell_showTitle && shell_title_glyphlayout != null){
				shell_title_thickness = Math.max(shell_icon.getHeight(), shell_title_glyphlayout.height) + (STATIC.shell_default_shell_title_pad * 2);
			}
			else if(!shell_showIcon && shell_showTitle && shell_title_glyphlayout != null){
				shell_title_thickness = shell_title_glyphlayout.height + (STATIC.shell_default_shell_title_pad * 2);
			}
			else if(shell_showIcon && shell_icon != null && !shell_showTitle){
				shell_title_thickness = shell_icon.getHeight() + (STATIC.shell_default_shell_title_pad * 2);
			}
			else{
				shell_title_thickness = STATIC.shell_default_shell_title_thickness;
			}
		}
		else{
			shell_title_thickness = STATIC.shell_default_shell_title_thickness;
		}
		if(shell_drawBorder){
			if(borderThickness <= 0){
				shell_border_thickness = STATIC.shell_default_shell_border_thickness;
			}
			else{
				shell_border_thickness = borderThickness;
			}
		}
		if(backgroundColor != null){
			shell_background_color = backgroundColor;
		}
		else{
			shell_background_color = STATIC.shell_default_color_background;
		}
		updateBounds();


	}
	private final void checkStyle(String style){
		if(style.contains(SHT.STANDARD)){
			shell_showTitle = true;
			shell_showIcon = true;
			shell_showMin = true;
			shell_showMax = true;
			shell_showClose = true;
			shell_showTitleBar = true;
			shell_drawBorder = true;
		}
		if(style.contains(SHT.TITLE)){
			shell_showTitle = true;
			shell_showTitleBar = true;
		}
		if(style.contains(SHT.ICON)){
			shell_showIcon = true;
			shell_showTitleBar = true;
		}
		if(style.contains(SHT.MIN)){
			shell_showMin = true;
			shell_showTitleBar = true;
		}
		if(style.contains(SHT.MAX)){
			shell_showMax = true;
			shell_showTitleBar = true;
		}
		if(style.contains(SHT.CLOSE)){
			shell_showClose = true;
			shell_showTitleBar = true;
		}
		if(style.contains(SHT.RESIZE)){
			shell_canResize = true;
		}
		if(style.contains(SHT.BORDER)){
			shell_drawBorder = true;
		}
	
	}

	private final void drawTitleBar(SpriteBatch batch){
		float xPad = shell_icon == null ? 0 : shell_icon.getWidth();
		shell_title_bitmapfont.setColor(shell_title_font_color);
		shell_title_bitmapfont.draw(batch, shell_title, shell_position_x + 10 + xPad , shell_position_y + (shell_size_height - STATIC.shell_default_shell_title_pad));
	}
	private final void drawBorder(ShapeRenderer fillRenderer){
		fillRenderer.setColor(shell_border_color);
		fillRenderer.box(shell_position_x, shell_position_y, 0, shell_size_width, shell_border_thickness, 0);
		if(!shell_showTitleBar){
			fillRenderer.box(shell_position_x, shell_position_y + (shell_size_height - shell_border_thickness), 0, shell_size_width, shell_border_thickness, 0);
		}
		else{
			fillRenderer.box(shell_position_x, shell_position_y + (shell_size_height - shell_title_thickness), 0, shell_size_width, shell_title_thickness, 0);
		}
		fillRenderer.box(shell_position_x, shell_position_y , 0, shell_border_thickness, shell_size_height, 0);
		fillRenderer.box(shell_position_x + (shell_size_width - shell_border_thickness), shell_position_y , 0, shell_border_thickness, shell_size_height, 0);
	}
	private final void drawBackground(ShapeRenderer fillRenderer){
		fillRenderer.setColor(shell_background_color);
		fillRenderer.box(shell_position_x, shell_position_y, 0, shell_size_width, shell_size_height, 0);
	}
	protected final void update(){

	}
	protected final void onMouseRollover(){

	}
	public final void dispose(){

	}
	@Override
	public void renderShell(ShapeRenderer fillRenderer, ShapeRenderer lineRenderer) {
		drawBackground(fillRenderer);
		if(shell_drawBorder){
			drawBorder(fillRenderer);
		}

	}
	@Override
	public void renderComponents(SpriteBatch batch) {
		if(shell_showTitle){
			drawTitleBar(batch);
		}

	}
	public final void open(){
		SHT.shellOpen(this);
	}
	public final void close(){
		SHT.shellClose(this);
	}
	protected final boolean inTitleArea(int x, int y){
		return shell_bounds_title_area.contains(x, y);
	}
	protected final boolean inLeftArea(int x, int y){
		return shell_bounds_border_left.contains(x, y);
	}
	public final void translate(float x, float y){
		shell_position_x += x;
		shell_position_y += y;
		updateBounds();
	}
	public final void translateSize(float width, float height){
		shell_size_width += width;
		shell_size_height += height;
		updateBounds();
	}
	public final void focus(){
		SHT.focusShell(this);
	}
	public final void unFocus(){
		SHT.unFocusShell(this);
	}
	public final void set(float x, float y){
		shell_position_x = x;
		shell_position_y = y;
		updateBounds();
	}
	public final void updateBounds(){
		shell_bounds_title_area = new Rectangle(shell_position_x + shell_border_thickness, shell_position_y + (shell_size_height - shell_title_thickness), shell_size_width - shell_border_thickness, shell_title_thickness);
		shell_bounds_border_left = new Rectangle(shell_position_x, shell_position_y + shell_border_thickness, shell_border_thickness, shell_size_height - (shell_border_thickness * 2));
	}
	static class STATIC{

		private static BitmapFont shell_default_font = new BitmapFont();

		private static Color shell_default_color_frame = Color.WHITE;

		private static Color shell_default_color_title_font = Color.BLACK;
		
		private static Color shell_default_color_background = Color.GRAY;

		private static float shell_default_shell_position_x = 50;

		private static float shell_default_shell_position_y = 50;

		private static float shell_default_shell_size_width = 600;

		private static float shell_default_shell_size_height = 400;

		private static float shell_default_shell_title_pad = 5;

		private static float shell_default_shell_title_thickness = 50 + (shell_default_shell_title_pad * 2);

		private static float shell_default_shell_border_thickness = 5;

	}


}
