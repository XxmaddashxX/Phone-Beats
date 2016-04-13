package net.driftingcolossus.phonebeats;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import net.driftingcolossus.phonebeats.framework.DeviceType;
import net.driftingcolossus.phonebeats.framework.Screen;
import net.driftingcolossus.phonebeats.framework.graphics.Fonts;
import net.driftingcolossus.phonebeats.framework.graphics.Graphics;
import net.driftingcolossus.phonebeats.framework.graphics.Textures;
import net.driftingcolossus.phonebeats.framework.user.hud.Hud;
import net.driftingcolossus.phonebeats.framework.user.sht.HudComposite;
import net.driftingcolossus.phonebeats.framework.user.sht.HudListener;
import net.driftingcolossus.phonebeats.framework.user.sht.HudShell;
import net.driftingcolossus.phonebeats.framework.user.sht.SHT;
import net.driftingcolossus.phonebeats.framework.user.sht.SHTProcessor;
import net.driftingcolossus.phonebeats.framework.user.sht.events.HudEvent;
import net.driftingcolossus.phonebeats.framework.user.sht.widgets.HudProgressBar;

public class PhoneBeats extends ApplicationAdapter {
	private Texture img;
	private ShapeRenderer linerenderer;
	private ShapeRenderer fillrenderer;
	public static Hud hud;
	private int current_tick;
	private static final int MAX_TICKS = 20;
	private static final int SECOND_IN_MILLI = 1000;
	private static final double MAX_UPDATE_LENGHT = 0.05;
	private float delta;
	private HudShell shell;
	private BitmapFont font;
	private HudComposite composite;
	private HudProgressBar progressbar;

	private static DeviceType application_device;
	
	public PhoneBeats(DeviceType type){
		application_device = type;
	}
	
	@Override
	public void create() {
		new HudShell(SHT.MAX + SHT.CLOSE + SHT.MIN + SHT.TITLE);
		Fonts.load();
		Textures.load();
		Screen.createAndRegister();
		hud = new Hud();   
		//HudGroup group = UI.newMainMenuScene();
		//hud.addComponentGroup(group);
		//hud.show(group);
		shell = new HudShell(SHT.BORDER + SHT.TITLE);
		composite = new HudComposite(shell, SHT.STANDARD);
		progressbar = new HudProgressBar(composite, SHT.BORDER + SHT.HORIZONTAL);
		progressbar.setBounds(20, 20, 200, 50);
		progressbar.addListener(SHT.UpdateTick, new HudListener(){

			@Override
			public void handleEvent(HudEvent event) {
				
			}
			
		});
		this.linerenderer = new ShapeRenderer();
		this.fillrenderer = new ShapeRenderer();
		Gdx.input.setInputProcessor(new SHTProcessor());
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		this.current_tick = 0;
		this.delta = 0.0f;
		shell.open();
	}

	@Override
	public void render() {
		this.delta += Gdx.graphics.getDeltaTime();
		if ((double)this.delta >= (double)this.current_tick * 0.05) {
			this.update();
		}
		this.mainRender();
	}

	private void mainRender() {
		Graphics.Static.clearFBOs();
		Graphics.clear();
		Screen.SpriteBatch_HUD().setProjectionMatrix(Screen.Camera_Main().combined.setToOrtho2D(0.0f, 0.0f, Screen.SCREEN_VIEWPORT_WIDTH, Screen.SCREEN_VIEWPORT_HEIGHT));
		this.fillrenderer.setProjectionMatrix(Screen.Camera_Main().combined.setToOrtho2D(0.0f, 0.0f, Screen.SCREEN_VIEWPORT_WIDTH, Screen.SCREEN_VIEWPORT_HEIGHT));
		linerenderer.setProjectionMatrix(Screen.Camera_Main().combined.setToOrtho2D(0.0f, 0.0f, Screen.SCREEN_VIEWPORT_WIDTH, Screen.SCREEN_VIEWPORT_HEIGHT));
		Graphics.begin(Screen.SCREEN_FBO_MAIN);
		SHT.render(Screen.SpriteBatch_HUD(), fillrenderer, linerenderer);
		
		Graphics.begin(Screen.SCREEN_BATCH_HUD);
		shell.renderComponents(Screen.SpriteBatch_HUD());
		Graphics.end(Screen.SCREEN_BATCH_HUD);
		Graphics.end(Screen.SCREEN_FBO_MAIN);
		Graphics.draw(Screen.SCREEN_FBO_MAIN);
		
		Graphics.update(Screen.SCREEN_CAMERA_MAIN);
	}

	@Override
	public void resize(int width, int height) {
		Screen.resize(width, height);
	}

	@Override
	public void dispose() {
		hud.dispose();
		SHT.shutdown();
		Screen.disposeAll();
		this.fillrenderer.dispose();
		this.linerenderer.dispose();
		Graphics.dispose();
		Textures.dispose();
		Fonts.dispose();
	}

	private void update() {
		this.current_tick++;
		hud.update();
		HudEvent event = new HudEvent();
		event.eventType = SHT.UpdateTick;
		SHT.sendEvent(event);
		if (this.current_tick == 20) {
			this.current_tick = 1;
		}
	}
	public static boolean isDesktop(){
		return application_device.equals(DeviceType.Desktop);
	}
	public static boolean isAndroid(){
		return application_device.equals(DeviceType.Android);
	}
	public static boolean isiOS(){
		return application_device.equals(DeviceType.iOS);
	}
	public static boolean isDevEnviroment(){
		return application_device.equals(DeviceType.Development);
	}
}
