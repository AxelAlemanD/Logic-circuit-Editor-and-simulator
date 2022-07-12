package com.upv.pm_2022.iti_27849_u2_equipo_01;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;

import com.github.mikephil.charting.data.Entry;

public class SimulationService extends IntentService {

    private Handler mHandler;
//    private int xAxies = -1;
//    private Boolean tempValue = false;
//    private int count = 0;

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
//            count++;
//            if(count == 1000) {
//                tempValue = DragAndDropView.figures.get(DragAndDropView.figures.size() - 1).getOutput();
//                MainActivity.outputValues.add(new Entry(xAxies++, (tempValue) ? 1 : 0 ));
//                MainActivity.outputValues.add(new Entry(xAxies, (tempValue) ? 1 : 0 ));
//                count = 0;
//            }
        }
        this.stopSelf();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent,flags,startId);
    }
}