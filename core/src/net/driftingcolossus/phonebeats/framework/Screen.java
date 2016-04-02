package net.driftingcolossus.phonebeats.framework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import net.driftingcolossus.phonebeats.framework.graphics.Graphics;

public class Screen {

	public static final float SCREEN_VIEWPORT_WIDTH = 800.0f;
    public static final float SCREEN_VIEWPORT_HEIGHT = 600.0f;
    public static final float SCREEN_ASPECT_RATIO = Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
    public static int SCREEN_VIEWPORT;
    public static int SCREEN_FBO_MAIN;
    public static int SCREEN_CAMERA_MAIN;
    public static int SCREEN_BATCH_MAIN;
    public static int SCREEN_BATCH_HUD;
    private static StretchViewport screen_viewport;
    private static FrameBuffer screen_fbo_main;
    private static OrthographicCamera screen_camera_main;
    private static SpriteBatch screen_batch_main;
    private static SpriteBatch screen_batch_hud;

    public static void createAndRegister() {
        screen_camera_main = new OrthographicCamera();
        SCREEN_CAMERA_MAIN = Graphics.register(screen_camera_main);
        screen_viewport = new StretchViewport(800.0f, 600.0f, screen_camera_main);
        SCREEN_VIEWPORT = Graphics.register(screen_viewport);
        screen_viewport.apply(true);
        screen_fbo_main = new FrameBuffer(Pixmap.Format.RGBA8888, 800, 600, false);
        SCREEN_FBO_MAIN = Graphics.register(screen_fbo_main);
        screen_batch_main = new SpriteBatch();
        SCREEN_BATCH_MAIN = Graphics.register(screen_batch_main);
        screen_batch_hud = new SpriteBatch();
        SCREEN_BATCH_HUD = Graphics.register(screen_batch_hud);
    }

    public static SpriteBatch SpriteBatch_Main() {
        return screen_batch_main;
    }

    public static SpriteBatch SpriteBatch_HUD() {
        return screen_batch_hud;
    }

    public static StretchViewport Viewport() {
        return screen_viewport;
    }

    public static OrthographicCamera Camera_Main() {
        return screen_camera_main;
    }

    public static FrameBuffer FrameBuffer() {
        return screen_fbo_main;
    }

    public static final void disposeAll() {
        screen_batch_main.dispose();
        screen_batch_hud.dispose();
        screen_fbo_main.dispose();
        Graphics.setFullDispose(true);
        Gdx.app.exit();
    }
	
	
}
