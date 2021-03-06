package com.upv.pm_2022.iti_27849_u2_equipo_01.Services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;

import androidx.annotation.RequiresApi;

import com.github.mikephil.charting.data.Entry;
import com.upv.pm_2022.iti_27849_u2_equipo_01.Dialogs.OutputGraphDialog;
import com.upv.pm_2022.iti_27849_u2_equipo_01.MainActivity;
import com.upv.pm_2022.iti_27849_u2_equipo_01.Point;

public class GenerateGraphService extends IntentService {

    private Handler mHandler;
    private Boolean tempValue = false;
    public static volatile boolean shouldContinue = true;

    public GenerateGraphService() {
        super("GenerateGraphService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onHandleIntent(Intent intent) {
        while(shouldContinue) {
            tempValue = Point.getLastGateOfTheCircuit().getOutput();
            MainActivity.outputGraphDialog.addEntry(new Entry(MainActivity.xAxiesGraph++, (tempValue) ? 1 : 0 ));
            MainActivity.outputGraphDialog.addEntry(new Entry(MainActivity.xAxiesGraph, (tempValue) ? 1 : 0 ));

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