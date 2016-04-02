package net.driftingcolossus.phonebeats.framework.graphics;

import java.io.File;
import java.io.FileFilter;

public class TTFFilter	implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        System.out.println(pathname.getName());
        if (pathname.getName().endsWith(".ttf")) {
            return true;
        }
        return false;
    }

}
