package net.driftingcolossus.phonebeats.framework.user.sht;

import java.util.ArrayList;
import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class SHT{

	//static final int YYYY = 1 << 0;	

	static final String NONE = "a";

	//Shell

	/**
	 * Used in {@link HudShell}
	 */
	public static final String STANDARD = "b";	
	/**
	 * Used in {@link HudShell}
	 */
	public static final String TITLE = "c";
	/**
	 * Used in {@link HudShell}
	 */
	public static final String MIN = "d";
	/**
	 * Used in {@link HudShell}
	 */
	public static final String MAX = "e";
	/**
	 * Used in {@link HudShell}
	 */
	public static final String CLOSE = "f";
	/**
	 * <p>The Style character used for defining a widget to use borders</p>
	 * <br>
	 * <table border = "1">
  	<col width="25%"/>
  	<col width="75%"/>
  	<thead>
    	<tr><th>Component</th><th>Effect</th></tr>
  	</thead>
  		<tbody>
     		<tr><td>{@link HudShell}</td><td>Defines if to draw Shell edges</td></tr>
     		<tr><td>{@link HudProgressBar}</td><td>Defines if to draw a border around progress bar</td></tr>
  		</tbody>
	</table>
	 * 
	 * 
	 * 
	 */
	public static final String BORDER = "g";
	/**
	 * Used in {@link HudShell}
	 */
	public static final String ICON = "h";
	/**
	 * Used in {@link HudShell}
	 */
	public static final String RESIZE = "i";


	public static final int Selection = 1;

	public static final int Moved = 2;

	public static final int Resized = 3;

	public static final int Disposed = 4;

	public static final int Verify = 5;

	public static final int KeyUp = 6;

	public static final int KeyDown = 7;

	public static final int KeyTyped = 8;

	public static final int MouseUp = 9;

	public static final int MouseDown = 10;

	public static final int MouseScroll = 11;

	public static final int MouseMoved = 12;

	public static final int MouseDragged = 13;

	public static final int Focus = 14;

	public static final int UnFocus = 15;

	public static final int Enable = 16;

	public static final int Disable = 17;

	public static final int UpdateTick = 18;

	public static final int RenderTick = 19;



	private static HudShell sht_focused_shell;

	private static HudWidget sht_focused_widget;


	private static final Stack<HudShell> sht_openhudshellsstack = new Stack<HudShell>();








	public static final void render(SpriteBatch batch, ShapeRenderer fillRenderer, ShapeRenderer lineRenderer){
		for(HudShell shell: sht_openhudshellsstack){
			fillRenderer.begin(ShapeType.Filled);
			lineRenderer.begin(ShapeType.Line);
			shell.renderShell(fillRenderer, lineRenderer);
			lineRenderer.end();
			fillRenderer.end();
		}
	}
	protected static final void shellOpen(HudShell shell){
		sht_openhudshellsstack.push(shell);
		sht_focused_shell = shell;
	}
	protected static final void shellClose(HudShell shell){
		sht_openhudshellsstack.remove(shell);
		if(!sht_openhudshellsstack.isEmpty()){
			sht_focused_shell = sht_openhudshellsstack.peek();
		}
	}
	protected static final void focusWidget(HudWidget widget){
		sht_focused_widget = widget;
	}
	protected static final void unFocusWidget(HudWidget widget){
		sht_focused_widget = null;
	}
	protected static final void focusShell(HudShell shell){
		sht_focused_shell = shell;
	}
	protected static final void unFocusShell(HudShell shell){
		sht_focused_shell = null;
	}
	protected static final HudShell[] getOpenShellStack(){
		HudShell[] array = new HudShell[sht_openhudshellsstack.size()];
		for(int i = 0; i < sht_openhudshellsstack.size(); i++){
			array[i] = sht_openhudshellsstack.get(i);
		}
		return array;
	}
	protected static final HudShell getFocusedShell(){
		return sht_focused_shell;
	}

}
