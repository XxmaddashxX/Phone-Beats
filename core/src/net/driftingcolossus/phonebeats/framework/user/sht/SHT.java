package net.driftingcolossus.phonebeats.framework.user.sht;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Stack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import net.driftingcolossus.phonebeats.framework.user.sht.events.HudEvent;
import net.driftingcolossus.phonebeats.framework.user.sht.widgets.HudProgressBar;

public class SHT{

	private static boolean shutdown = false;
	
	//static final int YYYY = 1 << 0;	

	public static final String NONE = "a";

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

	public static final String ANNOTATE = "j";

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
     		<tr><td>{@link HudProgressBar}</td><td>Defines if to draw the ProgressBar horizontally</td></tr>
  		</tbody>
	</table>
	 * 
	 * 
	 * 
	 */
	public static final String HORIZONTAL = "k";
	
	public static final String VERTICAL = "l";
	
	public static final String TRANSITION = "m";
	
	public static final String RETAIN = "n";
	
	public static final String FIT = "o";
	
	public static final String NODRAW = "p";
	
	public static final String MAINTAIN = "q";
	
	
	
	public static final int None = 0;
	
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

	public static final int Completion= 20;


	private static HudShell sht_focused_shell;

	private static HudWidget sht_focused_widget;


	private static final Stack<HudShell> sht_openhudshellsstack = new Stack<HudShell>();

	private static final ArrayList<HudResource> sht_initialisedResources = new ArrayList<HudResource>();

	private static final ArrayList<HudEventTable> sht_initialisedTables = new ArrayList<HudEventTable>();

	public static final void renderShells(ShapeRenderer fillRenderer, ShapeRenderer lineRenderer){
		if(shutdown){ return;}
		for(HudShell shell: sht_openhudshellsstack){
			fillRenderer.begin(ShapeType.Filled);
			lineRenderer.begin(ShapeType.Line);
			shell.renderShell(fillRenderer, lineRenderer);
			lineRenderer.end();
			fillRenderer.end();
		}
	}


	public static final void render(SpriteBatch batch){
		if(shutdown){ return;}
		for(HudShell shell: sht_openhudshellsstack){
			shell.renderComponents(batch);
		}
	}
	protected static final void shellOpen(HudShell shell){
		sht_openhudshellsstack.push(shell);
		sht_focused_shell = shell;
		System.out.println("OPEN");
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
	protected static final void initResource(HudResource resource){
		sht_initialisedResources.add(resource);
	}
	protected static final void disposeResource(HudResource resource){
		sht_initialisedResources.remove(resource);
	}
	protected static final void addTable(HudEventTable table){
		sht_initialisedTables.add(table);
	}
	public static final void sendEvent(HudEvent event){
		for(HudEventTable table: sht_initialisedTables){
			table.sendEvent(event);
			
		}
	}
	public static final void sendEvent(HudEvent event, HudResource resource){
		for(HudEventTable table: sht_initialisedTables){
			if(table.getResource().equals(resource)){
				table.sendEvent(event);
			}
		}
	}
	public static final void shutdown(){
		shutdown = true;
		for(HudResource resource: sht_initialisedResources){
			resource.dispose();
		}
		sht_initialisedResources.clear();
	}
	public static final void update(int tick){
		for(HudResource res: sht_initialisedResources){
			res.onUpdateTick(tick);
		}
	}
	public static class Constants{
		
		//Shell
		public static BitmapFont SHELL_DEFAULT_TITLE_FONT = new BitmapFont();
		public static int SHELL_DEFAULT_POSITION_X = 0;
		public static int SHELL_DEFAULT_POSITION_Y = 0;
		public static int SHELL_DEFAULT_SIZE_WIDTH = 100;
		public static int SHELL_DEFAULT_SIZE_HEIGHT = 100;
		public static Texture SHELL_DEFAULT_ICON = null;
		public static Color SHELL_DEFAULT_SHELL_BORDER_COLOR = Color.WHITE;
		public static Color SHELL_DEFAULT_SHELL_BACKGROUND_COLOR = Color.GRAY;
		public static Color SHELL_DEFAULT_SHELL_TITLE_COLOR = Color.BLACK;
		
		//Progress Bar
		public static BitmapFont PROGRESS_DEFAULT_FONT = new BitmapFont();
		public static int PROGRESS_DEFAULT_MIN = 0;
		public static int PROGRESS_DEFAULT_MAX = 100;
		public static int PROGRESS_DEFAULT_SELECTION = 0;
		public static Color PROGRESS_DEFAULT_COLOR_BORDER = Color.BLACK;
		public static Color PROGRESS_DEFAULT_COLOR_BAR_NORMAL = Color.GREEN;
		public static Color PROGRESS_DEFAULT_COLOR_BAR_ERROR = Color.RED;
		public static Color PROGRESS_DEFAULT_COLOR_BAR_PAUSED = Color.YELLOW;
		
		//Menubar
		public static int MENUBAR_DEFAULT_HEIGHT = 20;
		
	}
	public static class Debug{
		
	}
	public static class IO{
		
		private static final String FILE_STYLE_HEADER = "$SHT Style File by Drifting Colossus. DO NOT MODIFY";
		
		public static final void saveSHTSyleSet(File file) throws IOException, IllegalArgumentException, IllegalAccessException{
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			Class<Constants> constants = SHT.Constants.class;
			writer.write(FILE_STYLE_HEADER);
			writer.newLine();
			for(Field field: constants.getFields()){
				writer.write(field.getName() + "=" + field.getType().getName() + "=" + field.get(field.getType()));
				writer.newLine();
			}
			writer.close();
		}
		public static final void readSHTStyleSet(File file) throws FileNotFoundException, IOException, NoSuchFieldException, SecurityException, ClassNotFoundException{
			BufferedReader read = new BufferedReader(new FileReader(file));
			String line;
			while((line = read.readLine())!= null){
				if(line.startsWith("$")){
					continue;
				}
				String[] data = line.split("=", 3);
				Field field = SHT.class.getField(data[0]);
				if(field != null){
					Class<?> c = Class.forName(data[1]);
					if(c != null){
						
					}
				}
				continue;
			}
			read.close();
		}
		
	}
	
}
