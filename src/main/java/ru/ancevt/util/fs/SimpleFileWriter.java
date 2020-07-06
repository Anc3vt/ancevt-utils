package ru.ancevt.util.fs;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ancevt
 */
public class SimpleFileWriter implements Closeable {

    private final File file;
    private FileOutputStream fileOutputStream;

    public SimpleFileWriter(final File file) {
        this.file = file;

        try {
            fileOutputStream = new FileOutputStream(file, true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SimpleFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public File getFile() {
        return file;
    }

    public void write(byte[] bytes) {
        try {
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void print(Object object) {
        write(String.valueOf(object).getBytes());
    }

    public void println(Object object) {
        print(object);
        print(String.format("%n"));
    }

    @Override
    public void close() throws IOException {
        fileOutputStream.close();
    }

    public static final void print(final File file, Object data) {
        final SimpleFileWriter simpleFilePrinter = new SimpleFileWriter(file);
        simpleFilePrinter.print(data);
        try {
            simpleFilePrinter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static final void println(final File file, Object data) {
        final SimpleFileWriter simpleFilePrinter = new SimpleFileWriter(file);
        simpleFilePrinter.println(data);
        try {
            simpleFilePrinter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static final void write(File file, byte[] bytes) {
        final SimpleFileWriter simpleFilePrinter = new SimpleFileWriter(file);
        simpleFilePrinter.write(bytes);
        try {
            simpleFilePrinter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

}
