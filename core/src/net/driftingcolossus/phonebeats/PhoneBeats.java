package net.driftingcolossus.phonebeats;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import net.driftingcolossus.phonebeats.framework.Screen;
import net.driftingcolossus.phonebeats.framework.graphics.Fonts;
import net.driftingcolossus.phonebeats.framework.graphics.Graphics;
import net.driftingcolossus.phonebeats.framework.graphics.Textures;
import net.driftingcolossus.phonebeats.framework.input.InputController;
import net.driftingcolossus.phonebeats.framework.user.hud.Hud;
import net.driftingcolossus.phonebeats.framework.user.hud.HudGroup;
import net.driftingcolossus.phonebeats.framework.user.hud.HudTexturePane;

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

    @Override
    public void create() {

      //  Fonts.load();
        Textures.load();
        Screen.createAndRegister();
        hud = new Hud();   
        HudGroup group = new HudGroup("test_group");
        group.addComponent(new HudTexturePane("test", 50, 50, Textures.getTexture("badlogic"), Textures.getTexture("badlogic2")));
        hud.addComponentGroup(group);
        hud.show(group);
        this.linerenderer = new ShapeRenderer();
        this.fillrenderer = new ShapeRenderer();
        Gdx.input.setInputProcessor(new InputController());
        this.current_tick = 0;
        this.delta = 0.0f;
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
        Screen.SpriteBatch_HUD().setProjectionMatrix(Screen.Camera_Main().combined.setToOrtho2D(0.0f, 0.0f, 800.0f, 600.0f));
        this.fillrenderer.setProjectionMatrix(Screen.Camera_Main().combined.setToOrtho2D(0.0f, 0.0f, 800.0f, 600.0f));
        Graphics.begin(Screen.SCREEN_FBO_MAIN);
        Graphics.begin(Screen.SCREEN_BATCH_HUD);
        this.fillrenderer.begin(ShapeRenderer.ShapeType.Filled);
        hud.render(Screen.SpriteBatch_HUD(), null, null);
        hud.renderControlPoints(this.fillrenderer, this.linerenderer);
        Screen.SpriteBatch_HUD().flush();
        this.fillrenderer.end();
        Graphics.end(Screen.SCREEN_BATCH_HUD);
        Graphics.end(Screen.SCREEN_FBO_MAIN);
        Graphics.draw(Screen.SCREEN_FBO_MAIN);
        Graphics.update(Screen.SCREEN_CAMERA_MAIN);
    }

    @Override
    public void resize(int width, int height) {
        Screen.Viewport().update(width, height);
    }

    @Override
    public void dispose() {
        Screen.disposeAll();
        this.fillrenderer.dispose();
        this.linerenderer.dispose();
        Graphics.dispose();
    }

    private void update() {
        this.current_tick++;
        hud.update();
        if (this.current_tick == 20) {
            this.current_tick = 1;
        }
    }
}
