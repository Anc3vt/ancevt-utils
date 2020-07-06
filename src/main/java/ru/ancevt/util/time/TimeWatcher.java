package ru.ancevt.util.time;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author ancevt
 */
public class TimeWatcher {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        final File file = new File("/tmp/test148");

        final TimeWatcher fsw = new TimeWatcher(500);
        fsw.setCallBack(() -> {
            System.out.println(file.length());
        });
        fsw.start();

        try (FileOutputStream fos = new FileOutputStream(file)) {
            for (int i = 0; i < 1000000000; i++) {
                fos.write(0);
            }
        }
        fsw.stop();
    }

    private static final int DEFAULT_INTERVAL = 1000;

    private int interval;
    private Runnable callBack;
    private boolean runnning;

    public TimeWatcher(int interval) {
        this.interval = interval;
    }

    public TimeWatcher() {
        this(DEFAULT_INTERVAL);
    }

    public Runnable getCallBack() {
        return callBack;
    }

    public void setCallBack(Runnable callBack) {
        this.callBack = callBack;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public final void start() {
        if (runnning) {
            return;
        }

        runnning = true;
        new Thread(() -> {
            while (runnning) {
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                if (callBack != null) {
                    if (runnning) {
                        callBack.run();
                    }
                }
            }

        }, "timeWatcher").start();
    }

    public final void stop() {
        runnning = false;
    }

}
