package net.driftingcolossus.phonebeats.framework.user.sht.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import net.driftingcolossus.phonebeats.framework.user.sht.HudComposite;
import net.driftingcolossus.phonebeats.framework.user.sht.HudWidget;
import net.driftingcolossus.phonebeats.framework.user.sht.SHT;

public class HudProgressBar extends HudWidget{

	public final int NORMAL = 0, PAUSED = 1, ERROR = 2, INTER = 3;

	public final String HEADER_PROGRESS = "progress";
	public final String HEADER_MIN = "min";
	public final String HEADER_MAX = "max";
	public final String HEADER_STYLE = "style";
	/**
	 *  Ex - 15%
	 */
	public final String HEADER_PERCENTAGE = "percentage";
	/**
	 *  Ex - 15/100
	 */
	public final String HEADER_FRACTION = "fraction";

	private boolean drawBorder;

	private boolean drawLabels;

	private boolean orientation_horizontal;
	
	private boolean orientation_vertical;
	
	private ShapeRenderer renderer;

	private int progress_selection;

	private int progress_min;

	private int progress_max;

	private int progress_type;

	private String progress_labels_format;

	private BitmapFont progress_labels_font;

	private Color progress_border_color;

	private Color progress_bar_color;

	private GlyphLayout progress_labels_font_glyphlayout;

	public HudProgressBar(HudComposite composite, String style) {
		super(composite, style);	
		renderer = new ShapeRenderer();
		initialize(null, NORMAL, true, 0,0,0, style);	
	}
	public HudProgressBar(HudComposite composite, int lookStyle, String style) {
		super(composite, style);	
		renderer = new ShapeRenderer();
		initialize(null, lookStyle, true, 0,0,0, style);	
	}
	public HudProgressBar(HudComposite composite, int selection , int min, int max, int lookStyle, String style) {
		super(composite, style);	
		renderer = new ShapeRenderer();
		initialize(null, lookStyle, false, min, max, selection, style);	
	}
	private void initialize(BitmapFont font, int lookStyle, boolean useDefaults, int min, int max, int selection, String style){
		progress_labels_format = HEADER_PERCENTAGE;
		if(useDefaults){
			progress_min = SHT.Constants.PROGRESS_DEFAULT_MIN;
			progress_max = SHT.Constants.PROGRESS_DEFAULT_MAX;
			progress_selection = SHT.Constants.PROGRESS_DEFAULT_SELECTION;
		}
		orientation_horizontal = true;
		orientation_vertical = false;
		if(style.contains(SHT.HORIZONTAL)){
			orientation_horizontal = true;
			orientation_vertical = false;
		}
		if(style.contains(SHT.VERTICAL)){
			orientation_horizontal = false;
			orientation_vertical = true;
		}
		if(style.contains(SHT.BORDER)){
			drawBorder = true;
		}
		if(style.contains(SHT.ANNOTATE)){
			drawLabels = true;
		}
		progress_labels_font = (font == null) ? SHT.Constants.PROGRESS_DEFAULT_FONT : font;
		progress_labels_font_glyphlayout = new GlyphLayout();
		progress_bar_color = Color.RED;
		progress_border_color = Color.RED;
	}
	@Override
	protected void render(SpriteBatch batch) {
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.setTransformMatrix(batch.getTransformMatrix());
		renderer.setColor(progress_bar_color);
		renderer.begin(ShapeType.Filled);
		renderer.rect(widget_x, widget_y, orientation_horizontal ? getSelectionToDraw() : widget_width, orientation_vertical ? getSelectionToDraw() : widget_height);
		renderer.end();
		if(drawBorder){
			renderer.setColor(progress_border_color);
			renderer.begin(ShapeType.Line);
			renderer.rect(widget_x, widget_y, widget_width, widget_height);
			renderer.end();
		}
		batch.flush();
		if(drawLabels){
			String text = "HEY";
			if(progress_labels_font != null && progress_labels_font_glyphlayout != null){
				progress_labels_font_glyphlayout.setText(progress_labels_font, text);
				progress_labels_font.setColor(Color.WHITE);
				progress_labels_font.draw(batch, text, 50, 50);
			}
		}
	}
	private final String formatText(){
		String label = progress_labels_format;
		if(label.contains(HEADER_PROGRESS)){
			label = label.replaceAll(HEADER_PROGRESS, "" + getSelection());
		}
		if(label.contains(HEADER_MIN)){
			label = label.replaceAll(HEADER_MIN, "" + getMin());
		}
		if(label.contains(HEADER_MAX)){
			label = label.replaceAll(HEADER_MAX, "" + getMax());
		}
		if(label.contains(HEADER_PERCENTAGE)){
			label = label.replaceAll(HEADER_PERCENTAGE, getSelectionAsPercentage());
		}
		if(label.contains(HEADER_FRACTION)){
			label = label.replaceAll(HEADER_FRACTION, getSelectionAsFraction());
		}
		return label;
	}
	public final int getSelection(){
		return progress_selection;
	}
	public final void setSelection(int selection){
		if(selection > getMax()){
			progress_selection = getMax();
		}
		else if(selection < getMin()){
			progress_selection = getMin();
		}
		else{
			progress_selection = selection;
		}
	}
	public final int getMin(){
		return progress_min;
	}
	public final int getMax(){
		return progress_max;
	}
	public final String getSelectionAsFraction(){
		return getSelection() + "/" + getMax();
	}
	public final String getSelectionAsPercentage(){
		return getSelection() / getMax() + "%";
	}
	public void switchBarStyle(int style){

	}
	private final int getSelectionToDraw(){
		return (int)(getSelection() * ((orientation_vertical ? widget_height : widget_width) / getMax()));
	}
	@Override
	public void onDispose() {
		renderer.dispose();
	}
	






}
