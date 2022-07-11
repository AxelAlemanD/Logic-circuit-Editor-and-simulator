package com.upv.pm_2022.iti_27849_u2_equipo_01;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;

public class SimulationService extends IntentService {

    private Handler mHandler;

    public SimulationService() {
        super("SimulationService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        while(MainActivity.is_running) {
            for (Figure figure : DragAndDropView.figures) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (figure.getOutput())
                            figure.active();
                        else
                            figure.disable();
                    }
                });
            }
        }
        this.stopSelf();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent,flags,startId);
    }
}