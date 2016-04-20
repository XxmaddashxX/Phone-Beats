package net.driftingcolossus.phonebeats;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import net.driftingcolossus.phonebeats.framework.DeviceType;
import net.driftingcolossus.phonebeats.framework.LanguageSet;
import net.driftingcolossus.phonebeats.framework.Screen;
import net.driftingcolossus.phonebeats.framework.Strings;
import net.driftingcolossus.phonebeats.framework.graphics.Fonts;
import net.driftingcolossus.phonebeats.framework.graphics.Graphics;
import net.driftingcolossus.phonebeats.framework.graphics.Textures;
import net.driftingcolossus.phonebeats.framework.graphics.UI;
import net.driftingcolossus.phonebeats.framework.user.hud.Hud;
import net.driftingcolossus.phonebeats.framework.user.sht.HudComposite;
import net.driftingcolossus.phonebeats.framework.user.sht.HudShell;
import net.driftingcolossus.phonebeats.framework.user.sht.SHT;
import net.driftingcolossus.phonebeats.framework.user.sht.SHTProcessor;
import net.driftingcolossus.phonebeats.framework.user.sht.events.HudEvent;
import net.driftingcolossus.phonebeats.framework.user.sht.widgets.HudMenuBar;
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
	private HudMenuBar menubar;
	private ShaderProgram shader;
	public static float time;
	private static DeviceType application_device;
	
	public PhoneBeats(DeviceType type){
		application_device = type;
	}
	
	@Override
	public void create() {
		Fonts.load();
		Textures.load();
		try {
			Strings.load(LanguageSet.ENG_UK);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Screen.createAndRegister();
		this.linerenderer = new ShapeRenderer();
		this.fillrenderer = new ShapeRenderer();
		Gdx.input.setInputProcessor(new SHTProcessor());
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		this.current_tick = 0;
		this.delta = 0.0f;
		UI.newMainMenuScreen();
		ShaderProgram.pedantic = false;
	
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
		SHT.renderShells(fillrenderer, linerenderer);
		Graphics.begin(Screen.SCREEN_BATCH_HUD);
		SHT.render(Screen.SpriteBatch_HUD());
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
		SHT.update(current_tick);
		HudEvent event = new HudEvent();
		event.eventType = SHT.UpdateTick;
		SHT.sendEvent(event);
		if (this.current_tick == 20) {
			this.current_tick = 1;
			time++;
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
