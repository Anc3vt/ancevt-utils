package ru.ancevt.util.fs;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * @author ancevt
 */
public class SimpleFileReader {

    public static void main(String[] args) throws IOException {
        final String test = new Date().toString() + SimpleFileReader.readUtf8FromResources("test.sql") + ";";

        final File file = new File("/tmp/tmp.txt");
        if (file.exists()) {
            file.delete();
        }

        SimpleFileWriter.write(file, test.getBytes());

    }

    private File file;

    public SimpleFileReader(File file) {
        this.file = file;
    }

    public byte[] readAllBytes() throws IOException {
        final byte[] result;
        try (FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            while (fileInputStream.available() > 0) {
                final byte[] bytes = new byte[fileInputStream.available()];
                fileInputStream.read(bytes);
                baos.write(bytes);
            }
            result = baos.toByteArray();
        }

        return result;
    }

    public String readUtf8() throws IOException {
        return new String(readAllBytes());
    }

    public final static byte[] readAllBytes(File file) throws IOException {
        return new SimpleFileReader(file).readAllBytes();
    }

    public final static String readUtf8(File file) throws IOException {
        return new String(readAllBytes(file));
    }

    public final static byte[] readAllBytes(InputStream inputStream) throws IOException {
        final byte[] result;

        final int chunkSize = 512;

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            while (true) {
                final byte[] bytes = new byte[chunkSize];

                int len = inputStream.read(bytes);

                if (len == -1) {
                    break;
                }

                baos.write(bytes, 0, len);
            }
            result = baos.toByteArray();
        }
        inputStream.close();

        return result;
    }

    public final static String readUtf8(InputStream inputStream) throws IOException {
        return new String(readAllBytes(inputStream));
    }

    public final static byte[] readAllBytesFromResources(String resourceName) throws IOException {
        final InputStream inputStream = SimpleFileReader.class.getClassLoader().getResourceAsStream(resourceName);
        return readAllBytes(inputStream);
    }

    public final static String readUtf8FromResources(String resourceName) throws IOException {
        final InputStream inputStream = SimpleFileReader.class.getClassLoader().getResourceAsStream(resourceName);
        return readUtf8(inputStream);
    }
}
