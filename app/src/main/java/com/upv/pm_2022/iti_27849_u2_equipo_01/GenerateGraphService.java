package com.upv.pm_2022.iti_27849_u2_equipo_01;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import com.github.mikephil.charting.data.Entry;

public class GenerateGraphService extends IntentService {

    private Handler mHandler;
//    private int xAxies = -1;
    private Boolean tempValue = false;

    public GenerateGraphService() {
        super("GenerateGraphService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        while(MainActivity.is_running) {
            tempValue = DragAndDropView.figures.get(DragAndDropView.figures.size() - 1).getOutput();
            MainActivity.outputValues.add(new Entry(MainActivity.xAxiesGraph++, (tempValue) ? 1 : 0 ));
//            MainActivity.outputValues.add(new Entry(xAxies, (tempValue) ? 1 : 0 ));

            try {
                Thread.sleep(MainActivity.sampleRate * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.stopSelf();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent,flags,startId);
    }
}