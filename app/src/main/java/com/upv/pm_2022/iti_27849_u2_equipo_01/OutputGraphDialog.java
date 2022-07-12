package com.upv.pm_2022.iti_27849_u2_equipo_01;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class OutputGraphDialog extends Dialog {
    public Activity c;
    public Dialog d;

    public OutputGraphDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_circuit_output_graph);
        LineChart outputChart = findViewById(R.id.outputChart);

        LineDataSet lineDataSet = new LineDataSet(MainActivity.outputValues, "output");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);
        outputChart.setData(data);
        outputChart.invalidate();

        // Customize chart
        outputChart.getAxisLeft().setDrawGridLines(false);
        outputChart.getXAxis().setDrawGridLines(false);
        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setDrawCircles(false);
    }
}
