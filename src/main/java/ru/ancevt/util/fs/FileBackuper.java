package ru.ancevt.util.fs;

import java.io.File;

/**
 *
 * @author ancevt
 */
public class FileBackuper {
    public static final File getBackupOf(File file) {
        if(!file.exists()) return file;
        
        final File absFile = file.getAbsoluteFile();
        final File directory = file.getAbsoluteFile().getParentFile();
        
        final String fileName = file.getName();
        
        String nameWoExt = fileName;
        String ext = "";
        
        if(fileName.contains(".")) {
            final String[] s = fileName.split("\\.", 2);
            nameWoExt = s[0];
            ext = "." + s[1];
        }
        
        int i = 0;
        
        while(true) {
            final String path = directory.getAbsolutePath();
            final File result = new File(path + "/" + nameWoExt + i + ext);
            
            if(!result.exists()) return result;

            i++;
            if(i > 65536) break;
        }
        
        return null;
    }
}
