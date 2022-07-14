package com.upv.pm_2022.iti_27849_u2_equipo_01.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.upv.pm_2022.iti_27849_u2_equipo_01.R;

public class OutputGraphDialog extends Dialog {
    public Activity activity;
    private LineData data;
    private LineChart outputChart;

    public OutputGraphDialog(Activity activity) {
        super(activity);
        this.activity = activity;
        this.data = new LineData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circuit_output_graph);
        outputChart = findViewById(R.id.outputChart);
        outputChart.setData(this.data);

        // Customize chart
        outputChart.getAxisLeft().setDrawGridLines(false);
        outputChart.getXAxis().setDrawGridLines(false);
    }

    public void addEntry(Entry entry){
        LineData data = outputChart.getData();

        if(data != null){
            ILineDataSet set = data.getDataSetByIndex(0);
            if(set == null){
                set = createSet();
                data.addDataSet(set);
            }
        }

        data.addEntry(entry, 0);
        data.notifyDataChanged();
        outputChart.notifyDataSetChanged();
        outputChart.moveViewToX(data.getEntryCount());
    }

    private LineDataSet createSet() {
        LineDataSet set = new LineDataSet(null, "Output Data");
        set.setColor(Color.BLUE);
        set.setDrawCircles(false);
        return set;
    }
}
