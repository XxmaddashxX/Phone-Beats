package net.driftingcolossus.phonebeats.framework.graphics;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class Textures {

	private static final String TEXTURES_LOAD_DIR = "textures";
    private static final HashMap<String, Texture> loaded_textures = new HashMap();

    public static final void load() {
        Textures.loadTextures(Gdx.files.internal("textures").list());
    }

    private static void loadTextures(FileHandle[] files) {
        FileHandle[] arrfileHandle = files;
        int n = arrfileHandle.length;
        int n2 = 0;
        while (n2 < n) {
            FileHandle handle = arrfileHandle[n2];
            if (handle.isDirectory()) {
                Textures.loadTextures(handle.list());
            }
            if (handle.extension().equals("jpg") || handle.extension().equals("png")) {
                System.out.println(handle.name());
                loaded_textures.put(handle.nameWithoutExtension(), new Texture(handle));
            }
            ++n2;
        }
    }

    public static Texture getTexture(String key) {
        return loaded_textures.get(key);
    }
	
}
