package net.driftingcolossus.phonebeats.framework.user.sht.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import net.driftingcolossus.phonebeats.framework.user.sht.HudComposite;
import net.driftingcolossus.phonebeats.framework.user.sht.HudWidget;
import net.driftingcolossus.phonebeats.framework.user.sht.SHT;

public class HudProgressBar extends HudWidget{

	public final int NORMAL = 0, PAUSED = 1, ERROR = 2, INTER = 3;

	public final String HEADER_PROGRESS = "${progress}";
	public final String HEADER_MIN = "${min}";
	public final String HEADER_MAX = "${max}";
	public final String HEADER_STYLE = "${style}";
	/**
	 *  Ex - 15%
	 */
	public final String HEADER_PERCENTAGE = "${percentage}";
	/**
	 *  Ex - 15/100
	 */
	public final String HEADER_FRACTION = "${fraction}";

	private boolean drawBorder;

	private boolean drawLabels;

	private ShapeRenderer renderer;

	private int progress_selection;

	private int progress_min;

	private int progress_max;

	private int progress_type;

	private String progress_labels_format;

	private BitmapFont progress_labels_font;

	private Color progress_border_color;

	private Color progress_bar_color;

	public HudProgressBar(HudComposite composite, String style) {
		super(composite);	
		renderer = new ShapeRenderer();
		initialize(null, NORMAL, true, 0,0,0, style);	
	}
	public HudProgressBar(HudComposite composite, int lookStyle, String style) {
		super(composite);	
		renderer = new ShapeRenderer();
		initialize(null, lookStyle, true, 0,0,0, style);	
	}
	public HudProgressBar(HudComposite composite, int selection , int min, int max, int lookStyle, String style) {
		super(composite);	
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
		if(style.contains(SHT.BORDER)){
			drawBorder = true;
		}
		if(style.contains(SHT.ANNOTATE)){
			drawLabels = true;
		}
	}
	@Override
	protected void render(SpriteBatch batch) {
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.setColor(progress_bar_color);
		renderer.begin(ShapeType.Filled);
		
		renderer.end();
		if(drawBorder){
			renderer.setColor(progress_border_color);
			renderer.begin(ShapeType.Line);
			renderer.rect(widget_x, widget_y, widget_width, widget_height);
			renderer.end();
		}
		if(drawLabels){
			String text = formatText();
		}
	}
	private final String formatText(){
		String label = progress_labels_format;
		if(label.contains(HEADER_PROGRESS)){
			label.replaceAll(HEADER_PROGRESS, "" + getSelection());
		}
		if(label.contains(HEADER_MIN)){
			label.replaceAll(HEADER_MIN, "" + getMin());
		}
		if(label.contains(HEADER_MAX)){
			label.replaceAll(HEADER_MAX, "" + getMax());
		}
		if(label.contains(HEADER_PERCENTAGE)){
			label.replaceAll(HEADER_PERCENTAGE, getSelectionAsPercentage());
		}
		if(label.contains(HEADER_FRACTION)){
			label.replaceAll(HEADER_FRACTION, getSelectionAsFraction());
		}
		return label;
	}
	public final int getSelection(){
		return progress_selection;
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






}
