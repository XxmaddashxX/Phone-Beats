package net.driftingcolossus.phonebeats.framework.graphics;

import java.util.HashMap;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

import net.driftingcolossus.phonebeats.PhoneBeats;

public class Textures {

	private static final String TEXTURES_LOAD_DIR = "textures";
	private static final String TEXTURES_DEV_DIR = "bin/textures";
    private static final HashMap<String, Texture> loaded_textures = new HashMap<String, Texture>();

    public static final void load() {
        if(PhoneBeats.isDevEnviroment()){
        	Textures.loadTextures(Gdx.files.internal(TEXTURES_DEV_DIR).list());
        }
        else{
        	Textures.loadTextures(Gdx.files.internal(TEXTURES_LOAD_DIR).list());
        }
    
    }

    private static void loadTextures(FileHandle[] files) {
    	
    	for(FileHandle handle: files){
    		if(handle.isDirectory()){
    			Textures.loadTextures(handle.list());
    		}
    		if(handle.extension().equals("jpg") || handle.extension().equals("png")){
    			System.out.println(handle.nameWithoutExtension());
    			loaded_textures.put(handle.nameWithoutExtension(), new Texture(handle));
    		}
    	}
    	
    	/*FileHandle[] arrfileHandle = files;
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
        }*/
    }

    public static Texture getTexture(String key) {
        return loaded_textures.get(key);
    }
    public static final void dispose(){
    	Iterator<Texture> i = loaded_textures.values().iterator();
    	while(i.hasNext()){
    		i.next().dispose();
    	}
    }
	
}
