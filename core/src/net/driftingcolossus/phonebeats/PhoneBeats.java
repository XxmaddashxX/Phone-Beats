package net.driftingcolossus.phonebeats;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import net.driftingcolossus.phonebeats.framework.Screen;
import net.driftingcolossus.phonebeats.framework.graphics.Fonts;
import net.driftingcolossus.phonebeats.framework.graphics.Graphics;
import net.driftingcolossus.phonebeats.framework.graphics.Textures;
import net.driftingcolossus.phonebeats.framework.input.InputController;
import net.driftingcolossus.phonebeats.framework.user.hud.Hud;
import net.driftingcolossus.phonebeats.framework.user.sht.HudShell;
import net.driftingcolossus.phonebeats.framework.user.sht.SHT;

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
		this.linerenderer = new ShapeRenderer();
		this.fillrenderer = new ShapeRenderer();
		Gdx.input.setInputProcessor(new InputController());
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
		if (this.current_tick == 20) {
			this.current_tick = 1;
		}
	}
}
