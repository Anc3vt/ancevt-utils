package ru.ancevt.util.concurrency;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author ancevt
 */
public class ThreadPool {

    private final int capacity;
    private final List<Thread> list;

    private Runnable functionBetweenThreads;

    public ThreadPool(int capacity) {
        this.capacity = capacity;
        this.list = new CopyOnWriteArrayList<>();
    }

    public Runnable getFunctionBetweenThreads() {
        return functionBetweenThreads;
    }

    public void setFunctionBetweenThreads(Runnable functionBetweenThreads) {
        this.functionBetweenThreads = functionBetweenThreads;
    }

    public void addThread(Thread thread) {
        list.add(thread);
    }

    public void start() {
        int counter = capacity;

        final Thread[] threads = new Thread[capacity];

        int i = 0;

        while (counter > 0) {
            if (list.isEmpty()) {
                break;
            }
            final Thread thread = list.remove(0);
            thread.start();
            counter--;
            threads[i++] = thread;
        }

        for (Thread t : threads) {
            if (t != null) {
                try {
                    t.join();
                } catch (InterruptedException ex) {
                }
            }
        }
        
        if(functionBetweenThreads != null) {
            functionBetweenThreads.run();
        }

        if (!list.isEmpty()) {
            start();
        }
    }

    public void stop() {
        list.clear();
    }

    public int getCapacity() {
        return capacity;
    }

}
