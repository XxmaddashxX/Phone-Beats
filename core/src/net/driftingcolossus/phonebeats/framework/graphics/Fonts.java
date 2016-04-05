package net.driftingcolossus.phonebeats.framework.graphics;

import java.util.HashMap;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Fonts {
	
	private static final String FONT_FOLDER = "fonts";
    private static final int[] FONT_SIZES = new int[]{8, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 36, 40, 48, 50, 60, 70, 80, 90, 100};
    private static final HashMap<String, BitmapFont> font_bitmaps = new HashMap<String, BitmapFont>();

    public static final void load() {
        FileHandle[] files = Gdx.files.internal("bin/fonts").list(new TTFFilter());
        System.out.println(Gdx.files.internal("bin/fonts").path());
        if (files != null) {
            FileHandle[] arrfileHandle = files;
            int n = arrfileHandle.length;
            int n2 = 0;
            while (n2 < n) {
                FileHandle handle = arrfileHandle[n2];
                System.out.println(true);
                FreeTypeFontGenerator generator = new FreeTypeFontGenerator(handle);
                int[] arrn = FONT_SIZES;
                int n3 = arrn.length;
                int n4 = 0;
                while (n4 < n3) {
                    int i = arrn[n4];
                    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
                    parameter.size = i;
                    font_bitmaps.put(String.valueOf(handle.nameWithoutExtension()) + "_" + i, generator.generateFont(parameter));
                    ++n4;
                }
                generator.dispose();
                ++n2;
            }
        }
    }

    public static final BitmapFont get(String key) {
        return font_bitmaps.get(key);
    }
    public static final void dispose(){
    	Iterator<BitmapFont> i = font_bitmaps.values().iterator();
    	while(i.hasNext()){
    		i.next().dispose();
    	}
    }
	
}
