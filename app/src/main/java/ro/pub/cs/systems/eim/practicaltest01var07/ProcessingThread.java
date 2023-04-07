package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

import java.util.Date;
import java.util.Random;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;
    private Random random = new Random();

    private int my1, my2, my3, my4;

    public ProcessingThread(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has started! PID: " + Process.myPid() + " TID: " + Process.myTid());
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has stopped!");
    }

    private void sendMessage() {
        my1 = random.nextInt(10);
        my2 = random.nextInt(10);
        my3 = random.nextInt(10);
        my4 = random.nextInt(10);
        Intent intent = new Intent();
        intent.setAction(Constants.actionType);
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA, String.valueOf(my1) + " " + String.valueOf(my2) + " " + String.valueOf(my3) + " " + String.valueOf(my4));
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
