package com.upv.pm_2022.iti_27849_u2_equipo_01.Services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;

import androidx.annotation.RequiresApi;

import com.github.mikephil.charting.data.Entry;
import com.upv.pm_2022.iti_27849_u2_equipo_01.DragAndDropView;
import com.upv.pm_2022.iti_27849_u2_equipo_01.Figure;
import com.upv.pm_2022.iti_27849_u2_equipo_01.InputControls.ClockControl;
import com.upv.pm_2022.iti_27849_u2_equipo_01.MainActivity;
import com.upv.pm_2022.iti_27849_u2_equipo_01.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimulationService extends IntentService {
    public static volatile boolean shouldContinue = true;
    private Handler mHandler;

    public SimulationService() {
        super("SimulationService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onHandleIntent(Intent intent) {
        List<Figure> gates = DragAndDropView.figures.stream().filter(gate ->
                !(gate.getClass().getSimpleName().contains("Clock"))
        ).collect(Collectors.toList());

        List<Figure> clocks = DragAndDropView.figures.stream().filter(gate ->
                gate.getClass().getSimpleName().contains("Clock")
        ).collect(Collectors.toList());

        if(clocks.size() > 0) {
            for(Figure clock : clocks)
                runClockControl(clock);
        }
        while(shouldContinue) {
            System.out.println(shouldContinue);
            for (Figure figure : gates) {
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

    private void runClockControl(Figure clock){
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while(shouldContinue) {
                            try {
                                Thread.sleep(((ClockControl) clock).duration * 1000);
                                clock.getOutput();
                                Thread.sleep(1000);
                                clock.getOutput();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            ).start();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent,flags,startId);
    }
}