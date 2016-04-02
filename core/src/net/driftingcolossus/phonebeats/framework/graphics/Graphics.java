package net.driftingcolossus.phonebeats.framework.graphics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Graphics {

	public static final String VERSION = "1.00.100.0000";
    public static final String CHANGELOG = "";
    private static final Random RANDOM = new Random();
    private static final ArrayList<Integer> graphics_ids = new ArrayList();
    private static final HashMap<Integer, SpriteBatch> graphics_batches = new HashMap();
    private static final HashMap<Integer, Camera> graphics_cameras = new HashMap();
    private static final HashMap<Integer, FrameBuffer> graphics_buffers = new HashMap();
    private static final HashMap<Integer, ShaderProgram> graphics_shaders = new HashMap();
    private static final HashMap<Integer, Viewport> graphics_viewports = new HashMap();
    private static final SpriteBatch graphics_buffer_batch = new SpriteBatch();
    private static boolean graphics_boolean_flipX = false;
    private static boolean graphics_boolean_flipY = true;
    private static boolean graphics_complete_dispose = false;

    public static final int generateID() {
        int id;
        while (graphics_ids.contains(id = RANDOM.nextInt())) {
        }
        return id;
    }

    public static final int register(SpriteBatch batch) {
        int id = Graphics.generateID();
        graphics_batches.put(id, batch);
        graphics_ids.add(id);
        Debug.write(2, "TEMPLATE_DEFAULT", new OutputVariable<String>("$$category$$", "REGISTERS"), new OutputVariable<String>("$$message$$", "Registered SpriteBatch with ID $$id$$"), new OutputVariable<Integer>("$$id$$", id), new OutputVariable<Integer>("$$batchmax$$", batch.maxSpritesInBatch));
        return id;
    }

    public static final int register(FrameBuffer buffer) {
        int id = Graphics.generateID();
        graphics_buffers.put(id, buffer);
        graphics_ids.add(id);
        Debug.write(2, "TEMPLATE_DEFAULT", new OutputVariable<String>("$$category$$", "REGISTERS"), new OutputVariable<String>("$$message$$", "Registered FrameBuffer (FBO) with ID $$id$$"), new OutputVariable<Integer>("$$id$$", id));
        return id;
    }

    public static final int register(Camera camera) {
        int id = Graphics.generateID();
        graphics_cameras.put(id, camera);
        graphics_ids.add(id);
        Debug.write(2, "TEMPLATE_DEFAULT", new OutputVariable<String>("$$category$$", "REGISTERS"), new OutputVariable<String>("$$message$$", "Registered Camera with ID $$id$$"), new OutputVariable<Integer>("$$id$$", id));
        return id;
    }

    public static final int register(ShaderProgram program) {
        int id = Graphics.generateID();
        if (!program.isCompiled()) {
            System.out.println(program.getLog());
        }
        graphics_shaders.put(id, program);
        graphics_ids.add(id);
        Debug.write(2, "TEMPLATE_DEFAULT", new OutputVariable<String>("$$category$$", "REGISTERS"), new OutputVariable<String>("$$message$$", "Registered ShaderProgram with ID $$id$$"), new OutputVariable<Integer>("$$id$$", id));
        return id;
    }

    public static final int register(Viewport port) {
        int id = Graphics.generateID();
        graphics_viewports.put(id, port);
        graphics_ids.add(id);
        Debug.write(2, "TEMPLATE_DEFAULT", new OutputVariable<String>("$$category$$", "REGISTERS"), new OutputVariable<String>("$$message$$", "Registered Viewport with ID $$id$$"), new OutputVariable<Integer>("$$id$$", id));
        return id;
    }

    public static final void begin(int id) {
        if (graphics_batches.containsKey(id)) {
            graphics_batches.get(id).begin();
            Debug.write(2, "TEMPLATE_DEFAULT", new OutputVariable<String>("$$category$$", "SPRITEBATCH"), new OutputVariable<String>("$$message$$", "Batch with ID: $$id$$ has started drawing"), new OutputVariable<Integer>("$$id$$", id));
            return;
        }
        if (graphics_buffers.containsKey(id)) {
            graphics_buffers.get(id).begin();
            Debug.write(2, "TEMPLATE_DEFAULT", new OutputVariable<String>("$$category$$", "FRAMEBUFFER"), new OutputVariable<String>("$$message$$", "Framebuffer with ID: $$id$$ has begun"), new OutputVariable<Integer>("$$id$$", id));
            return;
        }
        if (graphics_shaders.containsKey(id)) {
            graphics_shaders.get(id).begin();
            Debug.write(2, "TEMPLATE_DEFAULT", new OutputVariable<String>("$$category$$", "SHADERPROGRAM"), new OutputVariable<String>("$$message$$", "Shaderprogram with ID: $$id$$ has begun"), new OutputVariable<Integer>("$$id$$", id));
            return;
        }
    }

    public static final void end(int id) {
        if (graphics_batches.containsKey(id)) {
            graphics_batches.get(id).end();
            Debug.write(2, "TEMPLATE_DEFAULT", new OutputVariable<String>("$$category$$", "SPRITEBATCH"), new OutputVariable<String>("$$message$$", "Batch with ID: $$id$$ has ended drawing"), new OutputVariable<Integer>("$$id$$", id));
            return;
        }
        if (graphics_buffers.containsKey(id)) {
            graphics_buffers.get(id).end();
            Debug.write(2, "TEMPLATE_DEFAULT", new OutputVariable<String>("$$category$$", "FRAMEBUFFER"), new OutputVariable<String>("$$message$$", "Framebuffer with ID: $$id$$ has finished"), new OutputVariable<Integer>("$$id$$", id));
            return;
        }
        if (graphics_shaders.containsKey(id)) {
            graphics_shaders.get(id).end();
            Debug.write(2, "TEMPLATE_DEFAULT", new OutputVariable<String>("$$category$$", "SHADERPROGRAM"), new OutputVariable<String>("$$message$$", "ShaderProgram with ID: $$id$$ has finished"), new OutputVariable<Integer>("$$id$$", id));
            return;
        }
    }

    public static final void flush(int id) {
        if (graphics_batches.containsKey(id)) {
            graphics_batches.get(id).flush();
            return;
        }
    }

    public static final void update(int id) {
        graphics_cameras.get(id).update();
    }

    public static final void apply(int viewid) {
        graphics_viewports.get(viewid).apply();
    }

    public static final void clear() {
        Debug.write(2, "TEMPLATE_DEFAULT", new OutputVariable<String>("$$category$$", "SCREEN"), new OutputVariable<String>("$$message$$", "Screen Cleared"));
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(16640);
    }

    public static final void draw(int id) {
        Graphics.draw(id, 0.0f, 0.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public static final void draw(int id, float x, float y, float width, float height) {
        Texture tex = (Texture)graphics_buffers.get(id).getColorBufferTexture();
        TextureRegion reg = new TextureRegion(tex);
        reg.flip(graphics_boolean_flipX, graphics_boolean_flipY);
        graphics_buffer_batch.begin();
        graphics_buffer_batch.draw(reg, x, y, width, height);
        graphics_buffer_batch.end();
    }

    public static final TextureRegion grab(int id) {
        return Graphics.grab(id, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public static final TextureRegion grab(int id, int x, int y, int width, int height) {
        Texture tex = (Texture)graphics_buffers.get(id).getColorBufferTexture();
        TextureRegion reg = new TextureRegion(tex, x, y, width, height);
        reg.flip(graphics_boolean_flipX, graphics_boolean_flipY);
        return reg;
    }

    public static final /* varargs */ void remove(int ... ids) {
        int[] arrn = ids;
        int n = arrn.length;
        int n2 = 0;
        while (n2 < n) {
            int i = arrn[n2];
            if (graphics_batches.containsKey(i)) {
                graphics_batches.remove(graphics_batches.get(i));
            }
            if (graphics_cameras.containsKey(i)) {
                graphics_cameras.remove(graphics_cameras.get(i));
            }
            if (graphics_viewports.containsKey(i)) {
                graphics_viewports.remove(graphics_viewports.get(i));
            }
            if (graphics_buffers.containsKey(i)) {
                graphics_buffers.remove(graphics_viewports.get(i));
            }
            if (graphics_shaders.containsKey(i)) {
                graphics_shaders.remove(graphics_shaders.get(i));
            }
            graphics_ids.remove(i);
            ++n2;
        }
    }

    public static final /* varargs */ void removeAndDispose(int ... ids) {
        int[] arrn = ids;
        int n = arrn.length;
        int n2 = 0;
        while (n2 < n) {
            int i = arrn[n2];
            if (graphics_batches.containsKey(i)) {
                graphics_batches.get(i).dispose();
                graphics_batches.remove(graphics_batches.get(i));
            }
            if (graphics_cameras.containsKey(i)) {
                graphics_cameras.remove(graphics_cameras.get(i));
            }
            if (graphics_viewports.containsKey(i)) {
                graphics_viewports.remove(graphics_viewports.get(i));
            }
            if (graphics_buffers.containsKey(i)) {
                graphics_buffers.get(i).dispose();
                graphics_buffers.remove(graphics_viewports.get(i));
            }
            if (graphics_shaders.containsKey(i)) {
                graphics_shaders.get(i).dispose();
                graphics_shaders.remove(graphics_shaders.get(i));
            }
            graphics_ids.remove((Object)i);
            ++n2;
        }
    }

    private static final void disposeAll() {
        Iterator<SpriteBatch> i = graphics_batches.values().iterator();
        while (i.hasNext()) {
            i.next().dispose();
        }
        Iterator<ShaderProgram> p = graphics_shaders.values().iterator();
        while (p.hasNext()) {
            p.next().dispose();
        }
        Iterator<FrameBuffer> o = graphics_buffers.values().iterator();
        while (o.hasNext()) {
            o.next().dispose();
        }
    }

    public static final void dispose() {
        graphics_buffer_batch.dispose();
    }

    public static final void flipX() {
        graphics_boolean_flipX = !graphics_boolean_flipX;
    }

    public static final void flipY() {
        graphics_boolean_flipY = !graphics_boolean_flipY;
    }

    public static final void applyShader(int id) {
        graphics_buffer_batch.setShader(graphics_shaders.get(id));
    }

    public static final void setFullDispose(boolean bool) {
        graphics_complete_dispose = bool;
    }

    public static final class Buffers {
        public static final FrameBuffer newDefaultBuffer() {
            return new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
        }

        public static final FrameBuffer newPixelatedBuffer(float width, float height, int pixelsX, int pixelsY) {
            return new FrameBuffer(Pixmap.Format.RGBA8888, (int)(width / (float)pixelsX), (int)(height / (float)pixelsY), false);
        }
    }

    public static final class Cameras {
        public static final PerspectiveCamera newPerspectiveCamera(float FOV, float near, float far) {
            PerspectiveCamera cam = new PerspectiveCamera(FOV, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            cam.near = near;
            cam.far = far;
            return cam;
        }

        public static final OrthographicCamera newOrthographicsCamera() {
            return new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }

    public static class CommandController {
        public static final boolean parseInput(String string) throws IOException {
            if (string.startsWith("genReport(")) {
                Debug.generateFileReport(new File(string.substring(string.indexOf("("), string.lastIndexOf(")") + 1)));
            }
            return false;
        }
    }

    public static final class Debug {
        public static final int INFO_NONE = 0;
        public static final int INFO_ALL = 1;
        public static final int INFO_DEBUG = 2;
        public static final int INFO_ERROR = 3;
        public static final int INFO_WARNINGS = 4;
        public static final int INFO_INFO = 5;
        public static final String INSERT_INFO_LEVEL = "$$level$$";
        public static final String INSERT_MESSAGE = "$$message$$";
        public static final String INSERT_CATEGORY = "$$category$$";
        public static final String INSERT_ERROR = "$$error$$";
        public static final String INSERT_ID = "$$id$$";
        public static final String INSERT_BATCH_MAX = "$$batchmax$$";
        public static final String INSERT_BATCH_DRAW_TOTAL = "$$batchtotal$$";
        public static final String INSERT_BATCH_DRAW_SINCE_LAST = "$$batchdrawlast$$";
        public static final String INSERT_BATCH_ISDRAWING = "$$batchdrawing$$";
        public static final String INSERT_BATCH_MATRIX_PRESENT = "$$batchmatrixpresent$$";
        public static final String INSERT_SHADER_COMPILED = "$$shadercompiled$$";
        public static final String INSERT_SHADER_LOG = "$$shaderlog$$";
        public static final String INSERT_SHADER_TOSTRING = "$$shadertostring$$";
        public static final String TEMPLATE_DEFAULT = "TEMPLATE_DEFAULT";
        public static final String TEMPLATE_BATCH_INFO = "TEMPLATE_BATCHINFO";
        public static final String TEMPLATE_SHADER_LOG = "TEMPLATE_SHADERLOG";
        public static final String CATEGORY_SCREEN = "SCREEN";
        public static final String CATEGORY_REGISTERS = "REGISTERS";
        public static final String CATEGORY_SPRITEBATCH = "SPRITEBATCH";
        public static final String CATEGORY_SHADERPROGRAM = "SHADERPROGRAM";
        public static final String CATEGORY_EXCEPTION = "EXCEPTION";
        public static final String CATEGORY_VIEWPORT = "VIEWPORT";
        public static final String CATEGORY_CAMERA = "CAMERA";
        public static final String CATEGORY_FRAMEBUFFER = "FRAMEBUFFER";
        public static final String CATEGORY_IO = "IO";
        public static final String CATEGORY_LIBGDX = "LIBGDX";
        public static final String DEFAULT_IDENTIFIER = "[$$level$$] [$$category$$]";
        private static final HashMap<String, String> TEMPLATES = new HashMap();
        private static boolean debug_enabled = false;
        private static int[] debug_information = new int[1];
        private static String[] debug_blacklist = null;
        private static OutputStream debug_stream = null;

        static {
            TEMPLATES.put("TEMPLATE_DEFAULT", "[$$level$$] [$$category$$] $$message$$");
            TEMPLATES.put("TEMPLATE_BATCHINFO", "[$$level$$] [$$category$$] ID:$$id$$");
        }

        public static boolean isDebugEnabled() {
            return debug_enabled;
        }

        public static void toggle() {
            debug_enabled = !debug_enabled;
        }

        public static void toggle(boolean bool) {
            debug_enabled = bool;
        }

        public static void redirect(OutputStream stream) {
            debug_stream = stream;
        }

        public static /* varargs */ void write(int level, String template, OutputVariable<?> ... variables) {
            String message = TEMPLATES.get(template);
            if (debug_enabled) {
                int[] arrn = debug_information;
                int n = arrn.length;
                int n2 = 0;
                while (n2 < n) {
                    int i = arrn[n2];
                    if (i == level || i == 1) {
                        OutputVariable<?>[] arroutputVariable = variables;
                        int n3 = arroutputVariable.length;
                        int n4 = 0;
                        while (n4 < n3) {
                            OutputVariable var = arroutputVariable[n4];
                            if (debug_blacklist != null) {
                                String[] arrstring = debug_blacklist;
                                int n5 = arrstring.length;
                                int n6 = 0;
                                while (n6 < n5) {
                                    String s = arrstring[n6];
                                    if (var.getInsert().equals("$$category$$") && s.equals(var.getValue())) {
                                        return;
                                    }
                                    ++n6;
                                }
                            }
                            message = message.replace(var.getInsert(), var.getValue().toString());
                            ++n4;
                        }
                        if (message.contains("$$level$$")) {
                            message = message.replace("$$level$$", Debug.levelToString(level));
                        }
                        if (debug_stream != null) {
                            try {
                                debug_stream.write(message.getBytes());
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println(message);
                        }
                    }
                    ++n2;
                }
            }
        }

        public static /* varargs */ void setDebugLevels(int ... ints) {
            debug_information = ints;
        }

        public static String levelToString(int i) {
            switch (i) {
                case 2: {
                    return "DEBUG";
                }
                case 3: {
                    return "ERROR";
                }
                case 4: {
                    return "WARNING";
                }
                case 5: {
                    return "INFO";
                }
            }
            return "UNKNOWN";
        }

        public static /* varargs */ void setBlackList(String ... strings) {
            debug_blacklist = strings;
        }

        public static void generateFileReport(File file) throws IOException {
            BufferedWriter write = new BufferedWriter(new FileWriter(file));
            write.write("Drifting Colossus Graphics Class Report ");
            write.newLine();
            write.write("");
            write.newLine();
            write.write("");
            write.newLine();
            write.write("Properties");
            write.newLine();
            write.write("{");
            write.newLine();
            write.write("  Version: 1.00.100.0000");
            write.newLine();
            write.write("}");
            write.newLine();
            write.write("");
            write.newLine();
            write.write("");
            write.newLine();
            write.write("");
            write.newLine();
            write.write("************************************");
            write.newLine();
            write.write("********    Report Start    ********");
            write.newLine();
            write.write("************************************");
            write.newLine();
            write.write("");
            write.newLine();
            write.write("");
            write.newLine();
            write.write("Registers:{");
            write.newLine();
            write.write("  SpriteBatches:{");
            write.newLine();
            Iterator<Entry<Integer, SpriteBatch>> i = graphics_batches.entrySet().iterator();
            int num = 0;
            while (i.hasNext()) {
                Entry<Integer, SpriteBatch> e = i.next();
                write.write("    " + num + "[ID: " + e.getKey() + "  Total Calls: " + ((SpriteBatch)e.getValue()).maxSpritesInBatch + "  Is Drawing: " + ((SpriteBatch)e.getValue()).isDrawing() + "]");
                write.newLine();
                ++num;
            }
            write.write("  }");
            write.newLine();
            write.write("}");
            write.newLine();
            write.close();
        }
    }

    public static final class Grabber {
        public static final ShaderProgram grabShader(int id) {
            return (ShaderProgram)graphics_shaders.get(id);
        }

        public static final Viewport grabViewport(int id) {
            return (Viewport)graphics_viewports.get(id);
        }

        public static final SpriteBatch grabSpritebatch(int id) {
            return (SpriteBatch)graphics_batches.get(id);
        }

        public static final Camera getCamera(int id) {
            return (Camera)graphics_cameras.get(id);
        }

        public static final FrameBuffer getFrameBuffer(int id) {
            return (FrameBuffer)graphics_buffers.get(id);
        }
    }

    public static final class Shaders {
        public static final String DEFAULT_VERTEX_SHADER = "attribute vec4 a_position;    \nattribute vec4 a_color;\nattribute vec2 a_texCoord0;\nuniform mat4 u_projTrans;\nvarying vec4 v_color;varying vec2 v_texCoords;void main()                  \n{                            \n   v_color = vec4(1, 1, 1, 1); \n   v_texCoords = a_texCoord0; \n   gl_Position =  u_projTrans * a_position;  \n}\n ";

        public static final ShaderProgram newDefaultShaderProgram() {
            String fragmentShader = "#ifdef GL_ES\nprecision mediump float;\n#endif\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\nvoid main()                                  \n{                                            \n  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n}";
            ShaderProgram prog = new ShaderProgram("attribute vec4 a_position;    \nattribute vec4 a_color;\nattribute vec2 a_texCoord0;\nuniform mat4 u_projTrans;\nvarying vec4 v_color;varying vec2 v_texCoords;void main()                  \n{                            \n   v_color = vec4(1, 1, 1, 1); \n   v_texCoords = a_texCoord0; \n   gl_Position =  u_projTrans * a_position;  \n}\n ", fragmentShader);
            return prog;
        }

        public static final ShaderProgram newNightVisionShader() {
            String frag = "#ifdef GL_ES\nprecision mediump float;\n#endif\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\nvoid main()                                  \n{                                            \n  vec4 color = v_color *  texture2D(u_texture, v_texCoords);\n  float v = max(max(color.r, color.g), color.b);\n  gl_FragColor = vec4(0.0, v, 0.0, color.a);}";
            return new ShaderProgram("attribute vec4 a_position;    \nattribute vec4 a_color;\nattribute vec2 a_texCoord0;\nuniform mat4 u_projTrans;\nvarying vec4 v_color;varying vec2 v_texCoords;void main()                  \n{                            \n   v_color = vec4(1, 1, 1, 1); \n   v_texCoords = a_texCoord0; \n   gl_Position =  u_projTrans * a_position;  \n}\n ", frag);
        }

        public static final ShaderProgram newThermalScopeShader() {
            String frag = "#ifdef GL_ES\nprecision mediump float;\n#endif\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\nvoid main()                                  \n{                                            \n  vec4 color = v_color * texture2D(u_texture, v_texCoords);\n  float v = max(max(color.r, color.g), color.b);\n  gl_FragColor = vec4(0.0, v, 0.0, color.a);}";
            return null;
        }
    }

    public static final class Static {
        public static final void flushAllBatches() {
            Iterator batches = graphics_batches.values().iterator();
            while (batches.hasNext()) {
                ((SpriteBatch)batches.next()).flush();
            }
        }

        public static final void setBatchesMatrix(Matrix4 mat) {
            Iterator batches = graphics_batches.values().iterator();
            while (batches.hasNext()) {
                ((SpriteBatch)batches.next()).setProjectionMatrix(mat);
            }
        }

        public static final void clearFBOs() {
            for (FrameBuffer fbo : graphics_buffers.values()) {
                fbo.begin();
                Graphics.clear();
                fbo.end();
            }
        }
    }
	
	
}
