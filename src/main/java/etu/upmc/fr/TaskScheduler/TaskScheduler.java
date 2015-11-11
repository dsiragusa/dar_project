package etu.upmc.fr.TaskScheduler;

import org.springframework.scheduling.TaskScheduler;

/**
 * Created by iShavgula on 11/11/15.
 */
public class TaskScheduler {

    private class MessagePrinterTask implements Runnable {

        private String message;
        public MessagePrinterTask(String message) {
            this.message = message;
        }

        public void run() {
            taskScheduler.scheduleWithFixedDelay()
        }

    }

    private org.springframework.scheduling.TaskScheduler taskScheduler;

    public TaskScheduler(org.springframework.scheduling.TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    public void printMessages() {
        for(int i = 0; i < 25; i++) {
            taskExecutor.execute(new MessagePrinterTask("Message" + i));
        }
    }
}
