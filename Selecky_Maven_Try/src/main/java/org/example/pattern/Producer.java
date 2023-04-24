package org.example.pattern;

import org.example.service.Action;
import org.example.service.ActionService;

import java.util.Queue;

public class Producer implements Runnable {
    private final Queue<Action> queue;
    private final ActionService actionService;
    private volatile Boolean isRunning = true;

    public Producer(Queue<Action> queue, ActionService actionService) {
        this.queue = queue;
        this.actionService = actionService;
    }

    public void addTask(Action action){
        queue.add(action);
    }

    @Override
    public void run() {
        while (isRunning){
            synchronized (queue) {
                while (queue.isEmpty()){
                    try {
                        queue.wait();
                    } catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                        System.out.println("Producer interrupted");
                    }
                }
                Action action = queue.poll();
                if (action != null){
                    executeAction(action);
                }
            }
        }
    }

    public void stop(){
        isRunning = false;
    }

    private void executeAction (Action action) {
        switch (action.getActionType()) {
            case ADD -> actionService.add(action.getUser(), action.getSessionFactory());
            case PRINTALL -> actionService.printAll(action.getSessionFactory());
            case DELETEALL -> actionService.deleteAll(action.getSessionFactory());
            default -> System.out.println("Invalid action: " + action.getActionType());
        }
    }
}
