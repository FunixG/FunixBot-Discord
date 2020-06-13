package fr.funixgaming.bot.Modules;

import fr.funixgaming.bot.Main;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ScheduledTasks {

    public static void init() {
        new Thread(() -> {
            Timer timer = new Timer();

            new NotificationLive(timer, 10000);
        }).start();
    }

}

abstract class Task {
    Task(Timer timer, int period) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                task();
            }
        }, 0, period);
    }

    abstract void task();
}

class NotificationLive extends Task {

    private boolean isStreaming;

    NotificationLive(Timer timer, int period) {
        super(timer, period);
        this.isStreaming = false;
    }

    void task() {
        try {
            Main.twitchApi.fetchStream();
            boolean apiStream = Main.twitchApi.isLive;
            if (!isStreaming && apiStream) {
                System.out.println("STREAM START");
            } else if (isStreaming && !apiStream) {
                System.out.println("STREAM END");
            }
            this.isStreaming = apiStream;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
