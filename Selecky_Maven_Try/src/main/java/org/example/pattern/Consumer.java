package org.example.pattern;

import org.example.service.Action;

import java.util.Queue;

public class Consumer implements Runnable{
    private final Queue<Action> queue;
    private volatile boolean isRunning = true;

    public Consumer(Queue<Action> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (isRunning){
            synchronized (queue) {
                if (!queue.isEmpty()){
                    queue.poll();
                }
                queue.notifyAll();
            }
        }
    }

    public void stop() {
        isRunning = false;
    }
}
