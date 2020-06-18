package UI;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyThread implements Runnable{
    private boolean exit = false;
    private int currentTime;
    Timer timer = new Timer();
    TimerTask exitApp = new TimerTask() {
        @Override
        public void run() {
            System.exit(0);
        }
    };

    public MyThread(int currentTime) {
        this.currentTime=currentTime;
        timer.schedule(exitApp, new Date(System.currentTimeMillis()+10*1000+30*60*1000));
    }

    @Override
    public void run() {
        try {
            while (currentTime<30*60) {
                Thread.sleep(1000);
                currentTime++;
                System.out.println(currentTime + " seconds passed.");
            }
            Thread.currentThread().interrupt();
        } catch (InterruptedException ignored) {
        }
    }

    public void stop() {
        this.exit = true;
    }

    public int getCurrentTime() {
        return currentTime;
    }
}
